package com.mylogic.backends.lwjgl;


import com.mylogic.utils.ObjectMap;
import com.mylogic.utils.Clipboard;
import com.mylogic.utils.SnapshotArray;
import com.mylogic.Preferences;
import com.mylogic.backends.lwjgl.LwjglApplicationConfiguration;
import com.mylogic.Gdx;
import com.mylogic.Files;
import com.mylogic.Application;
import com.mylogic.ApplicationListener;
//import com.mylogic.Audio;
import com.mylogic.ApplicationLogger;
import com.mylogic.Gdx;
import com.mylogic.Graphics;
import com.mylogic.Input;
import com.mylogic.LifecycleListener;
import com.mylogic.utils.Array;
import com.mylogic.utils.GdxRuntimeException;
//import com.mylogic.Net;

import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class LwjglApplication implements Application {

    protected final LwjglGraphics graphics;
    //protected OpenALAudio audio;
    protected final LwjglFiles files;
    protected final LwjglInput input;
    //protected final Net net;
    protected final ApplicationListener listener;
    protected Thread mainLoopThread;
    protected boolean running = true;
    protected final Array<Runnable> runnables = new Array<Runnable>();
    protected final Array<Runnable> executedRunnables = new Array<Runnable>();
    protected final SnapshotArray<LifecycleListener> lifecycleListeners =
        new SnapshotArray<LifecycleListener>(LifecycleListener.class);
    protected int logLevel = Level.INFO.intValue();
    protected ApplicationLogger applicationLogger;
    protected String preferencesdir;
    protected Files.FileType preferencesFileType;


    public LwjglApplication (ApplicationListener listener, LwjglApplicationConfiguration config) {
            this(listener, config, new LwjglGraphics(config));
    }

    public LwjglApplication(ApplicationListener listener, LwjglApplicationConfiguration config,
                        LwjglGraphics graphics) {
        LwjglNativesLoader.load();
        setApplicationLogger(new LwjglApplicationLogger());

        if (config.title == null)
            config.title = listener.getClass().getSimpleName();
        this.graphics = graphics;
        /*if (!LwjglApplicationConfiguration.disableAudio) {
                try {
                    audio =
                            new OpenALAudio(config.audioDeviceSimultaneousSources, config.audioDeviceBufferCount,
                                            config.audioDeviceBufferSize);
                } catch (Throwable t) {
                    log("LwjglApplication",
                        "Couldn't initialize audio, disabling audio", t);
                    LwjglApplicationConfiguration.disableAudio = true;
                }
            }*/
        files = new LwjglFiles();
        input = new LwjglInput();
        //net = new LwjglNet();
        this.listener = listener;
        this.preferencesdir = config.preferencesDirectory;
        this.preferencesFileType = config.preferencesFileType;

        Gdx.app = this;
        Gdx.graphics = graphics;
        Gdx.files = files;
        Gdx.input = input;
        //Gdx.net = net;
        initialize();
    }

    private void initialize() {
        mainLoopThread = new Thread("LWJGL Application") {
                @Override
                public void run() {
                    graphics.setVSync(graphics.config.vSyncEnabled);
                    try {
                        //Application.this.mainLoop();
                        mainLoop();
                    } catch (Throwable t) {
                        //if (audio != null) audio.dispose();
                        Gdx.input.setCursorCatched(false);
                        if (t instanceof RuntimeException)
                            throw (RuntimeException)t;
                        else
                            throw new GdxRuntimeException(t);
                    }
                }
            };
        mainLoopThread.start();
    }

    void mainLoop() {
        SnapshotArray<LifecycleListener> lifecycleListeners =
            this.lifecycleListeners;

        try {
            graphics.setupDisplay();
        } catch (LWJGLException e) {
            throw new GdxRuntimeException(e);
        }

        listener.create();
        graphics.resize = true;

        int lastWidth = graphics.getWidth();
        int lastHeight = graphics.getHeight();

        graphics.lastTime = System.nanoTime();
        boolean wasActive = true;
        while (running) {
            Display.processMessages();
            if (Display.isCloseRequested())
                exit();

            boolean isActive = Display.isActive();
            if (wasActive &&
                !isActive) { // if it's just recently minimized from active state
                wasActive = false;
                synchronized (lifecycleListeners) {
                    LifecycleListener[] listeners = lifecycleListeners.begin();
                    for (int i = 0, n = lifecycleListeners.size; i < n; ++i)
                        listeners[i].pause();
                    lifecycleListeners.end();
                }
                listener.pause();
            }
            if (!wasActive &&
                isActive) { // if it's just recently focused from minimized state
                wasActive = true;
                synchronized (lifecycleListeners) {
                    LifecycleListener[] listeners = lifecycleListeners.begin();
                    for (int i = 0, n = lifecycleListeners.size; i < n; ++i)
                        listeners[i].resume();
                    lifecycleListeners.end();
                }
                listener.resume();
            }

            boolean shouldRender = false;

            if (graphics.canvas != null) {
                int width = graphics.canvas.getWidth();
                int height = graphics.canvas.getHeight();
                if (lastWidth != width || lastHeight != height) {
                    lastWidth = width;
                    lastHeight = height;
                    Gdx.gl.glViewport(0, 0, lastWidth, lastHeight);
                    listener.resize(lastWidth, lastHeight);
                    shouldRender = true;
                }
            } else {
                graphics.config.x = Display.getX();
                graphics.config.y = Display.getY();
                if (graphics.resize || Display.wasResized() ||
                    (int)(Display.getWidth() *
                          Display.getPixelScaleFactor()) !=
                    graphics.config.width ||
                    (int)(Display.getHeight() * Display.getPixelScaleFactor()) !=
                    graphics.config.height) {
                    graphics.resize = false;
                    graphics.config.width =
                            (int)(Display.getWidth() * Display.getPixelScaleFactor());
                    graphics.config.height =
                            (int)(Display.getHeight() * Display.getPixelScaleFactor());
                    Gdx.gl.glViewport(0, 0, graphics.config.width,
                                      graphics.config.height);
                    if (listener != null)
                        listener.resize(graphics.config.width,
                                        graphics.config.height);
                    graphics.requestRendering();
                }
            }

            if (executeRunnables())
                shouldRender = true;

            // If one of the runnables set running to false, for example after an exit().
            if (!running)
                break;

            input.update();
            shouldRender |= graphics.shouldRender();
            input.processEvents();
            //if (audio != null) audio.update();

            if (!isActive && graphics.config.backgroundFPS == -1)
                shouldRender = false;
            int frameRate =
                isActive ? graphics.config.foregroundFPS : graphics.config.backgroundFPS;
            if (shouldRender) {
                graphics.updateTime();
                graphics.frameId++;
                listener.render();
                Display.update(false);
            } else {
                // Sleeps to avoid wasting CPU in an empty loop.
                if (frameRate == -1)
                    frameRate = 10;
                if (frameRate == 0)
                    frameRate = graphics.config.backgroundFPS;
                if (frameRate == 0)
                    frameRate = 30;
            }
            if (frameRate > 0)
                Display.sync(frameRate);
        }

        synchronized (lifecycleListeners) {
            LifecycleListener[] listeners = lifecycleListeners.begin();
            for (int i = 0, n = lifecycleListeners.size; i < n; ++i) {
                listeners[i].pause();
                listeners[i].dispose();
            }
            lifecycleListeners.end();
        }
        listener.pause();
        listener.dispose();
        Display.destroy();
        //if (audio != null) audio.dispose();
        if (graphics.config.forceExit)
            System.exit(-1);
    }

    public boolean executeRunnables() {
        synchronized (runnables) {
            for (int i = runnables.size - 1; i >= 0; i--)
                executedRunnables.add(runnables.get(i));
            runnables.clear();
        }
        if (executedRunnables.size == 0)
            return false;
        do
            executedRunnables.pop().run();
        while (executedRunnables.size > 0);
        return true;
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return listener;
    }

    /*  @Override
                public Audio getAudio () {
                        return audio;
                }*/

    @Override
    public Files getFiles() {
        return files;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Input getInput() {
        return input;
    }

    /*   @Override
                public Net getNet () {
                        return net;
                }*/

    @Override
    public ApplicationType getType() {
        return ApplicationType.Desktop;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    public void stop() {
        running = false;
        try {
            mainLoopThread.join();
        } catch (Exception ex) {
        }
    }

    @Override
    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() -
            Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getNativeHeap() {
        return getJavaHeap();
    }

    ObjectMap<String, Preferences> preferences =
        new ObjectMap<String, Preferences>();

    @Override
    public Preferences getPreferences(String name) {
        if (preferences.containsKey(name)) {
            return preferences.get(name);
        } else {
            Preferences prefs =
                new LwjglPreferences(new LwjglFileHandle(new File(preferencesdir,
                                                                  name),
                                                         preferencesFileType));
            preferences.put(name, prefs);
            return prefs;
        }
    }

   /* @Override
    public Clipboard getClipboard() {
        return new LwjglClipboard();
    }*/

    @Override
    public void postRunnable(Runnable runnable) {
        synchronized (runnables) {
            runnables.add(runnable);
            Gdx.graphics.requestRendering();
        }
    }

    @Override
    public void debug(String tag, String message) {
        if (logLevel >= LOG_DEBUG)
            getApplicationLogger().debug(tag, message);
    }

    @Override
    public void debug(String tag, String message, Throwable exception) {
        if (logLevel >= LOG_DEBUG)
            getApplicationLogger().debug(tag, message, exception);
    }

    @Override
    public void log(String tag, String message) {
        if (logLevel >= LOG_INFO)
            getApplicationLogger().log(tag, message);
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        if (logLevel >= LOG_INFO)
            getApplicationLogger().log(tag, message, exception);
    }

    @Override
    public void error(String tag, String message) {
        if (logLevel >= LOG_ERROR)
            getApplicationLogger().error(tag, message);
    }

    @Override
    public void error(String tag, String message, Throwable exception) {
        if (logLevel >= LOG_ERROR)
            getApplicationLogger().error(tag, message, exception);
    }

    @Override
    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public int getLogLevel() {
        return logLevel;
    }

    @Override
    public void setApplicationLogger(ApplicationLogger applicationLogger) {
        this.applicationLogger = applicationLogger;
    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        return applicationLogger;
    }


    @Override
    public void exit() {
        postRunnable(new Runnable() {
                @Override
                public void run() {
                    running = false;
                }
            });
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        synchronized (lifecycleListeners) {
            lifecycleListeners.add(listener);
        }
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        synchronized (lifecycleListeners) {
            lifecycleListeners.removeValue(listener, true);
        }
    }
}

