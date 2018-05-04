/**  
* @Title: Client.java
* @Package com.test.client
* @Description: TODO(用一句话描述该文件做什么)
* @author lijingyang   
* @date 2018年5月4日 上午10:47:25
* @version V1.0  
*/
package com.test.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName: Client
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author li jingyang
 * @date 2018年5月4日 上午10:47:25
 *
 */
public class Client
{

	public int port = 8080;
	Socket socket = null;

	public static void main(String[] args)
	{
		new Client();
	}

	public Client()
	{

		try
		{
			socket = new Socket("127.0.0.1", port);

			new Cthread().start();

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg1;
			while ((msg1 = br.readLine()) != null)
			{

				System.out.println("msg1:"+msg1);
			}
		} catch (Exception e)
		{
		}
	}

	class Cthread extends Thread
	{

		public void run()
		{
			try
			{

				BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				String msg2;

				while (true)
				{

					msg2 = re.readLine();
					pw.println("msg2:"+msg2);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}

}
