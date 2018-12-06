package src.utils;

import src.utils.objects.*;

import java.util.ArrayList;
import java.util.List;

public final class Builder_Objects {

    public static Level createLevel(){ return new Level(); }
    public static Anim_Sprite createAnim_Sprite(){ return new Anim_Sprite(); }
    public static Animation_Objects createAnimation_Objects(){ return new Animation_Objects(); }
    public static Screen_Object createScreen_Object(){ return new Screen_Object(); }

    public final static Buildable_Objects createObjects(String name){
        System.out.println("name_create: " + name);
        if (name.equals(Level.getClassTag())) return createLevel();
        if (name.equals(Anim_Sprite.getClassTag())) return createAnim_Sprite();
        if (name.equals(Animation_Objects.getClassTag())) return createAnimation_Objects();
        if (name.equals(Screen_Object.getClassTag())) return createScreen_Object();

        throw new NullPointerException("Need to add new (Buildable_Objects) " + name + " object in 'Builder_Objects'!");
    }

    public final static List<Buildable_Objects> parseConfigObject_entitys(String parentTag, String classTag, String inputString) {
        int end = inputString.length();

        String tag = parentTag;
        System.out.println("tag: " + tag);

        inputString = inputString.substring(inputString.indexOf("<" + tag + ">") + ("<" + tag + ">").length(), inputString.indexOf("</" + tag + ">"));
        //print("input2" + inputString);

        //end = 0;
        List<Buildable_Objects> listObject_entitys = new ArrayList<Buildable_Objects>();
        String tag_2 = classTag;
        while (inputString.contains("<" + tag_2 + ">") && inputString.contains("</" + tag_2 + ">"))
        {
            end = inputString.indexOf("</" + tag_2 + ">");
            String lev = inputString.substring(0, end);

            Buildable_Objects curObject = createObjects(classTag);
            for (Params param : Params.values())
                if (curObject.isParam(param))
                {
                    //System.out.println("\t" + lev + "::" + param.toString());
                    if (curObject.isSimpleField(param))
                        curObject.setParam(param, getParam(lev, param.toString()));
                    else
                    if (lev.contains("<" + param.toString() + ">") && lev.contains("</" + param.toString() + ">"))
                    {
                        String tag_3 = param.toString();
                        String tag_2_ = tag_3.substring(0, tag_3.length() - 1);
                        lev = lev.substring(lev.indexOf("<" + tag_3 + ">") + ("<" + tag_3 + ">").length(), lev.indexOf("</" + tag_3 + ">"));
                        String inputString_ = lev;
                        while (inputString_.contains("<" + tag_2_ + ">") && inputString_.contains("</" + tag_2_ + ">"))
                        {
                            int end_ = inputString_.indexOf("</" + tag_2_ + ">");
                            String lev_ = inputString_.substring(0, end_ + ("</" + tag_2_ + ">").length());
                            curObject.setParam(param, getParam(lev_, tag_2_));

                            inputString_ = inputString_.substring(lev_.length());
                        }
                    }
                }
            listObject_entitys.add(curObject);

            inputString = inputString.substring(end + ("</" + tag_2 + ">").length());
        }
        //listDictionary_entitys.Reverse();
        return listObject_entitys;
    }

    private static String getParam(String str, String tag)
    {
        if (str.indexOf("</" + tag + ">") == -1) return null;
        return str.substring(str.indexOf("<" + tag + ">") + ("<" + tag + ">").length(), str.indexOf("</" + tag + ">"));
    }
}
