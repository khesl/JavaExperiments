package synctestthread;

class EggVoice extends Thread
{
	@Override
	public void run()
	{
		for(int i = 0; i < 5; i++)
		{
			try{
				sleep(1000);		//Приостанавливает поток на 1 секунду
			}catch(InterruptedException e){}
			
			System.out.println("egg!");	
		}
		//Слово «яйцо» сказано 5 раз
	}
}

public class ChickenVoice	//Класс с методом main()
{
	static EggVoice mAnotherOpinion;	//Побочный поток
	
	public static void main(String[] args)
	{
		mAnotherOpinion = new EggVoice();	//Создание потока
		System.out.println("Dispute started...");
		mAnotherOpinion.start(); 			//Запуск потока
		
		for(int i = 0; i < 5; i++)
		{
			try{
				Thread.sleep(1000);		//Приостанавливает поток на 1 секунду
			}catch(InterruptedException e){}
			
			System.out.println("chicken!");
		}
		
		//Слово «курица» сказано 5 раз

		if(mAnotherOpinion.isAlive())	//Если оппонент еще не сказал последнее слово
		{
			try{
				mAnotherOpinion.join();	//Подождать пока оппонент закончит высказываться.
			}catch(InterruptedException e){}
			
			System.out.println("First appear egg!");
		}
		else	//если оппонент уже закончил высказываться
		{
			System.out.println("First appear chicken!");
		}
		System.out.println("Dispute end!");	
	}
}