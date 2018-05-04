/**  
* @Title: Server.java
* @Package com.test
* @Description: TODO(用一句话描述该文件做什么)
* @author lijingyang   
* @date 2018年5月4日 上午10:46:11
* @version V1.0  
*/
package com.test.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Server
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author li jingyang
 * @date 2018年5月4日 上午10:46:11
 *
 */
public class Server
{
	int port;
	List<Socket> clients;
	ServerSocket server;

	public static void main(String[] args)
	{
		new Server();
	}

	public Server()
	{
		try
		{

			port = 8080;
			clients = new ArrayList<Socket>();
			server = new ServerSocket(port);

			while (true)
			{
				Socket socket = server.accept();
				clients.add(socket);
				Mythread mythread = new Mythread(socket);
				mythread.start();
			}

		} catch (Exception ex)
		{
		}
	}

	class Mythread extends Thread
	{
		Socket ssocket;
		private BufferedReader br;
		private PrintWriter pw;
		public String msg;

		public Mythread(Socket s)
		{
			ssocket = s;
		}

		public void run()
		{

			try
			{
				br = new BufferedReader(new InputStreamReader(ssocket.getInputStream()));
				msg = "欢迎【" + ssocket.getInetAddress() + ":"+ssocket.getPort()+"】进入聊天室！当前聊天室有【" + clients.size() + "】人";

				sendMsg();

				while ((msg = br.readLine()) != null)
				{

					msg = "【" + ssocket.getInetAddress() + ":"+ssocket.getPort()+"】说：" + msg;
					sendMsg();

				}
			} catch (Exception ex)
			{

			}
		}

		public void sendMsg()
		{
			try
			{
				System.out.println(msg);

				for (int i = clients.size() - 1; i >= 0; i--)
				{
					pw = new PrintWriter(clients.get(i).getOutputStream(), true);
					pw.println(msg);
					pw.flush();
				}
			} catch (Exception ex)
			{
			}
		}
	}

}
