
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;
import java.util.Timer;
/**
 *
 * @author lenovo
 */
 
public class Server extends JFrame implements ActionListener
{
    JPanel p5,po1,po2,po3,po4;
    JButton login,imgb1;
	JLabel jl2,jl3,jl4,jl5,jl6;
    ServerSocket MyService;
    Socket clientSocket = null;
    BufferedInputStream input;
    ImageIcon i1,i2,i3,i4;
	JTextField tf1;
   long endTime;
     DataOutputStream dos;
        DataInputStream dis;
    BorderLayout bl;
//BufferedInputStream input;
    TargetDataLine targetDataLine;

    BufferedOutputStream out;
    ByteArrayOutputStream byteArrayOutputStream;
    AudioFormat audioFormat;
String remoteIp;

InputStream ips;
OutputStream ops;
boolean flg=true;
//String ip="";
String msg="";
String imsg="";
Exception exob;	
byte bt[];
Timer timer = new Timer();
 
	
    SourceDataLine sourceDataLine;	  
    byte tempBuffer[] = new byte[1000];


	public Server()
	{
       initComponents();
  
       
    }
		
	
    

class ThreadConServ extends Thread
{
Socket clientSocket;
ThreadConServ(Socket sock2)
{
this.clientSocket=sock2;
}
public void run()
{
        
        try {    
    		audioFormat = getAudioFormat();
    		DataLine.Info dataLineInfo =  new DataLine.Info( 
SourceDataLine.class,audioFormat);
    		sourceDataLine = (SourceDataLine)
                AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                 captureAudio();
		input = new BufferedInputStream(clientSocket.getInputStream());	
		out = new BufferedOutputStream(clientSocket.getOutputStream());
		
			while(input.read(tempBuffer)!=-1){			
				sourceDataLine.write(tempBuffer,0,1000);
			}
		} catch (Exception e) {
			
			//senderArea.append("IOException\n");
                         System.out.println("IoException\n");
		} 
        
    }
private void captureAudio() {
		try {
			
			Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
			//senderArea.append("Available mixers:\n");
			System.out.println("Available Mixers: \n");
                        for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
  				//senderArea.append(mixerInfo[cnt].getName() + "\n");
                                System.out.println(mixerInfo[cnt].getName() + "\n");		
	
                          }
			
			audioFormat = getAudioFormat();

			DataLine.Info dataLineInfo = new DataLine.Info(
					TargetDataLine.class, audioFormat);

			Mixer mixer = AudioSystem.getMixer(mixerInfo[3]);

			targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);

			targetDataLine.open(audioFormat);
			targetDataLine.start();

			Thread CaptureThread = new CaptureThread();
			CaptureThread.start();		
		} catch (Exception e) {
			//senderArea.append("Exception\n");
                          System.out.println("Exception\n");		
}
	}

}
private AudioFormat getAudioFormat(){
		    float sampleRate = 8000.0F;		  
		    int sampleSizeInBits = 8;		   
		    int channels = 1;		    
		    boolean signed = true;		    
		    boolean bigEndian = false;		 
		    return new AudioFormat(
		                      sampleRate,
		                      sampleSizeInBits,
		                      channels,
		                      signed,
		                      bigEndian);
		  }
	
	class CaptureThread extends Thread {

CaptureThread()
{}
      Socket socket;
   
      public CaptureThread(Socket socket) {
         this.socket = socket;
      }
   

		byte tempBuffer[] = new byte[1000];

		public void run() {			
			try {
				while (true) {
					int cnt = targetDataLine.read(tempBuffer, 0,
							tempBuffer.length);
					
					out.write(tempBuffer);				
				}
				
			} catch (Exception e) {
				//senderArea.append("Exception\n");
			         System.out.println("Exception\n");
                            }
		}
	}
    
    
    public void initComponents()
    {

         bl=new BorderLayout();
        setLayout(bl);

        po1=new JPanel();
po1.setBackground(Color.black);
po2=new JPanel();
po2.setBackground(Color.black);
po3=new JPanel();
po3.setBackground(Color.black);
po4=new JPanel();
po4.setBackground(Color.black);
add(po1,BorderLayout.EAST);
add(po2,BorderLayout.WEST);
add(po3,BorderLayout.NORTH);
add(po4,BorderLayout.SOUTH);



     p5=new Bg5Panel();
	p5.setLayout(null);	
jl2 = new JLabel(" Welcome");
	jl2.setFont(new Font("Verdana",70,70));
         jl2.setBounds(10,10,700,140);     
i1=new ImageIcon("output_c0PApt.gif");
        login=new JButton(i1);
	login.setBounds(160,340,140,200);
login.setPreferredSize(new Dimension(90,50));

imgb1=new JButton();
imgb1.setPreferredSize(new Dimension(300,300));
imgb1.setIcon(i1);
        p5.add(jl2);

	p5.add(login);
	p5.add(imgb1);
	
	
	add(p5,BorderLayout.CENTER);
	

        
	login.addActionListener(this);
	
    }
public void actionPerformed(ActionEvent e)
{

 	if (e.getSource() == login)
        {
          

       try{
while(true)
{
          MyService = new ServerSocket(7700);
	clientSocket = MyService.accept();

/*ops=clientSocket.getOutputStream();
  ips=clientSocket.getInputStream();*/
          
          dis=new DataInputStream(clientSocket.getInputStream());
          dos=new DataOutputStream(clientSocket.getOutputStream());
		 remoteIp = clientSocket.getInetAddress().getHostAddress();
		System.out.println(remoteIp);
          int flag=dis.read();
         
           int flagcallee=0;

          if(flag==1)
           { 
  java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {



			Accept aa=new Accept();
                
			aa.setSize(800,600);  
			aa.setVisible(true); 
			aa.setResizable(false);         

}
        });
}
if(flag==2)    
{
System.out.println("Message");
try{
//MyService = new ServerSocket(7700);
	//clientSocket = MyService.accept();
 //dis=new DataInputStream(clientSocket.getInputStream());
          //dos=new DataOutputStream(clientSocket.getOutputStream());

}
catch(Exception e1)
            {
               System.out.println(e1);
            }
          


			Message aa=new Message();


                
			aa.setSize(800,600);  
			aa.setVisible(true);
			aa.setResizable(false); 
}
} 
}
catch(Exception e1)
            {
               System.out.println(e1);
            }


         
}
}




class Accept extends JFrame implements ActionListener{
    JPanel p5,po1,po2,po3,po4,p51,pp;
    JButton acptb,rjctb,holdb,imgb2,discb;
    ImageIcon i2;
	JTextField tfi;
	JLabel jli;
    
    BorderLayout bl;
	 CardLayout cl;

	 Accept()
	{
		initComponents();
	}
    
    public void initComponents()
    {
         bl=new BorderLayout();
        setLayout(bl);
cl=new CardLayout();
pp=new JPanel();
pp.setLayout(cl);	

        po1=new JPanel();
po1.setBackground(Color.black);
po2=new JPanel();
po2.setBackground(Color.black);
po3=new JPanel();
po3.setBackground(Color.black);
po4=new JPanel();
po4.setBackground(Color.black);
add(po1,BorderLayout.EAST);
add(po2,BorderLayout.WEST);
add(po3,BorderLayout.NORTH);
add(po4,BorderLayout.SOUTH);

//i2=new ImageIcon("g.jpg");

     p5=new Bg9Panel();
     p5.setLayout(null);	
jl5=new JLabel("Calling... ");
tfi=new JTextField(20);
tfi.setText(remoteIp);
tfi.setBounds(220,100,90,20);
tfi.setBackground(Color.yellow);
tfi.setForeground(Color.red);
tfi.setEnabled(false);  
jli=new JLabel("Caller IP");
tfi.setBounds(150,100,80,20);
jli.setBounds(60,100,80,20);
jli.setFont(new Font("Comic Sans MS",20,20)); 
       jl5.setBounds(60,20,200,60);
        jl5.setFont(new Font("Comic Sans MS",50,50));


    i2=new ImageIcon("telephone4.gif");
    
     acptb=new JButton(i2);
	acptb.setBounds(80,330,100,100);
	i3=new ImageIcon("voipimage25.png");
       
    rjctb=new JButton(i3);
	rjctb.setBounds(320,330,100,100);
      jl3=new JLabel("Accept");
        jl3.setBounds(90,430,80,20);
        jl3.setFont(new Font("Comic Sans MS",20,15));
	jl4=new JLabel("Reject");
        jl4.setBounds(330,430,80,20);
	jl4.setFont(new Font("Comic Sans MS",20,15));
   	//holdb=new JButton("Hold");
//	imgb2=new JButton();
 jl6=new JLabel("call duration:");
tf1=new JTextField(5);
acptb.setPreferredSize(new Dimension(90,50));
rjctb.setPreferredSize(new Dimension(90,50));
//holdb.setPreferredSize(new Dimension(90,50));
//imgb2.setPreferredSize(new Dimension(200,270));
//imgb2.setIcon(i2);

//p51=new JPanel();
discb=new JButton("Disconnect");

//p51.add(jl6);

	p5.add(acptb);
	p5.add(rjctb);
	//p5.add(holdb);
	p5.add(jl3);
	p5.add(jl4);
	p5.add(jl5);
       p5.add(tfi);
	p5.add(jli);
	//p5.add(jl6);
//p5.add(imgb2);


//pp.add(p5,"FIRST");
//pp.add(p51,"SECOND");
//	p5.setSize(500,500);
	p5.setVisible(true);
	add(p5,BorderLayout.CENTER);
	

        
	acptb.addActionListener(this);
	rjctb.addActionListener(this);
	//holdb.addActionListener(this);
        discb.addActionListener(this);
    }
	
public void actionPerformed(ActionEvent e)
 {
if (e.getSource() == acptb)
   	{


try{

       dos.write(1);
jl5.setText("..Connected..");
 Thread ThreadConServ=new ThreadConServ(clientSocket);
        ThreadConServ.start();  

//cl.show(pp,"SECOND");
       // mysender(); 

//long startTime = System.currentTimeMillis();
//int ind=dis.read();
//System.out.println(ind);

/*if(ind==1)
{
endTime   = System.currentTimeMillis();
}
long totalTime = endTime - startTime;
 System.out.println(totalTime);
dos.write(1);*/
}
catch(Exception e1)
{
System.out.println(e1);
}
       

   	}

if (e.getSource() == rjctb)
   	{

System.exit(0);         
//tf1.setText("10");
   	}

if (e.getSource() == holdb)
   	{
         //stf1.setText("10");
   	}
 }   
}
class Message extends JFrame implements ActionListener,TextListener
{
JPanel p4,po1,po2,po3,po4;
JTextArea ta1,ta2;
JButton sendb,back2;
PrintWriter pw=null;
BufferedReader br=null;
BorderLayout bl;

Message()
{
System.out.println("Message 2");
initComponents();
}
public void initComponents()
{
System.out.println("Message 2"); 

bl=new BorderLayout();
        setLayout(bl);
po1=new JPanel();
po1.setBackground(Color.black);
po2=new JPanel();
po2.setBackground(Color.black);
po3=new JPanel();
po3.setBackground(Color.black);
po4=new JPanel();
po4.setBackground(Color.black);
add(po1,BorderLayout.EAST);
add(po2,BorderLayout.WEST);
add(po3,BorderLayout.NORTH);
add(po4,BorderLayout.SOUTH);
p4=new JPanel();
p4.setLayout(null);	
ta1=new JTextArea(5,20);
ta1.setBounds(50,340,500,150);
ta1.setBackground(Color.pink);
	ta1.setFont(new Font("Comic Sans MS",10,15));
	ta2=new JTextArea(15,30);
	 ta2.setBounds(30,30,690,270);
           ta2.setBackground(Color.black);
	ta2.setForeground(Color.green);
	 ta2.setEnabled(false);  
	ta2.setFont(new Font("Comic Sans MS",10,15));
        i4=new ImageIcon("voipimage32.jpg");
JScrollPane scr = new JScrollPane(ta2,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);// Add your text area to scroll pane 
        //ta1.setBounds(5, 35, 385, 330);
        //ta2.setLineWrap(true);
        //ta2.setWrapStyleWord(true);
        scr.setBounds(35, 30, 690, 270);
          
sendb=new JButton(i4);
sendb.setBounds(590,360,100,100);
	back2=new JButton("Back");
	back2.setBounds(380,400,70,30);
	p4.add(scr);
	p4.add(ta1);
	p4.add(sendb);
	p4.add(back2);
add(p4,BorderLayout.CENTER);
//ta2.addTextListener(this);
sendb.addActionListener(this);
System.out.println("Message3");


Thread Connect=new Connect();
Connect.start();

}
class Connect extends Thread {

Connect()
{}
      Socket socket;
   
      public Connect(Socket socket) {
         this.socket = socket;
      }
   

		byte tempBuffer[] = new byte[1000];

		public void run() {			
			try {
                            while(flg)
				{
					try
					{
						//bt=new byte[100];
String st;
System.out.println("Message3");
						st=dis.readUTF();
System.out.println("Message3");
						ta2.append("\nClient:  "+st+"\n");
					}
					catch(Exception ex)
					{
						flg=false;
						/*cob.bt1.setEnabled(true);
						cob.bt1.setLabel("Start");
						cob.bt2.setEnabled(false);
						cob.tf1.setFocusable(true);
						cob.tf2.setFocusable(true);
						cob.ta2.setFocusable(false);
						dob=new dialog(cob.fob,"IO Error",1,msg);
						dob.setVisible(true);*/
					}
				}  
				
			} catch (Exception e) {
				//senderArea.append("Exception\n");
			         System.out.println("Exception\n");
                            }
		}
}

public void textValueChanged(TextEvent te)
		{
			
			if(ta2.getText().endsWith("\n"))
			{
				imsg=ta1.getText();
				ta2.append(":  "+imsg);
				ta1.setText("");
				if(true)
				{
					readdat();
				}
			}
		}



public void actionPerformed(ActionEvent e)
 {


if (e.getSource() == sendb)
{


try{
  
 System.out.println("hello");


//String input;


imsg=ta1.getText();
ta2.append("\nMe :"+imsg+"\n");
ta1.setText("");

if(true)
				{
					readdat();
				}


}
catch(Exception e1)
{
System.out.println(e1);
}
}

}


void readdat()
		{

			try
			{
				msg="Server is Disconnected.";
				exob=new Exception(msg);
				dos.writeUTF(imsg);
			}
			catch(Exception ex)
			{
				flg=false;
				/*bt1.setEnabled(true);
				bt1.setLabel("Start");
				bt2.setEnabled(false);
				tf1.setFocusable(true);
				tf2.setFocusable(true);
				ta2.setFocusable(false);
				dob=new dialog(fob,"IO Error",1,msg);
				dob.setVisible(true);*/
			}

}
}
}


class Bg5Panel extends JPanel {
    Image bg = new ImageIcon("backgrounds1.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

class Bg9Panel extends JPanel {
    Image bg = new ImageIcon("backgrounds3.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}


class appmy
{

public static void main(String s[])
{
Server vv=new Server();
vv.setTitle("--Voip--");
vv.setSize(800,600);
vv.setVisible(true);
vv.setResizable(false);

}
}

