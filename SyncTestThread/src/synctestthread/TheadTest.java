package synctestthread;

class Incremenator extends Thread {
    //О ключевом слове volatile - чуть ниже
    private volatile boolean mIsIncrement = true;
    private volatile boolean mFinish = false;

    public void changeAction() //Меняет действие на противоположное
    {
        mIsIncrement = !mIsIncrement;
    }

    public void finish() //Инициирует завершение потока
    {
        mFinish = true;
    }

    @Override
    public void run()
    {
                    do
                    {
                            if(!Thread.interrupted())       //Проверка прерывания
                            {
                                    if(mIsIncrement) TheadTest.mValue++;      //Инкремент
                                    else TheadTest.mValue--;                  //Декремент
                                    
                                    //Вывод текущего значения переменной
                                    System.out.print(TheadTest.mValue + " ");
                            }
                            else
                                    return;         //Завершение потока     

                            try{
                                    Thread.sleep(1000);             //Приостановка потока на 1 сек.
                            }catch(InterruptedException e){
                                    return; //Завершение потока после прерывания
                            }
                    }
                    while(true); 
            }
}

public class TheadTest {
    static Incremenator mInc;
    public static int mValue = 0;

    public TheadTest() {
        super();
    }

    public static void main(String[] args) {
        mInc = new Incremenator(); //Создание потока

        System.out.print("Value = ");

        mInc.start(); //Запуск потока
        

        //Троекратное изменение действия инкременатора
        //с интервалом в i*2 секунд
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(i * 2 * 1000); //Ожидание в течении i*2 сек.
            } catch (InterruptedException e) {
            }

            mInc.changeAction(); //Переключение действия
        }

        mInc.interrupt(); //Инициация завершения побочного потока
    }
}
