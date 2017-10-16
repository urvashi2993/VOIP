import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.net.URL; 
public class Voip extends JFrame implements ActionListener, ItemListener,TextListener
{

ServerSocket ss=null;
Socket sd=null;
BorderLayout bl;
CardLayout cl;
GridLayout gl;
JPanel p1,p2,pp,p3,p31,p4,p5,po1,po2,po3,po4,p6;
JButton nextb,achat,mchat,callb,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,sendb,acptb,rjctb,gridb,
back1,back2,back3,back4,imgb1,imgb2,imgb3,imgb4,discb,conb,holdb,quitb;
JTextField tf1,tf2,tf3,tf4,tf5;
JTextArea ta1,ta2;
JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl12,jl13,jl14,jl15,jl16,jl17,jl19;
ImageIcon i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14;
JComboBox cb,cb1;
AudioClip click1,click2;

String s1="";
String s2="";
String s3="";
private String s4="";
InputStream ips;
OutputStream ops;
boolean flg=true;
String ip="";
String msg="";
String imsg="";
Exception exob;
byte bt[];
		

    boolean stopCapture = false;

	ByteArrayOutputStream byteArrayOutputStream;

	AudioFormat audioFormat;

	TargetDataLine targetDataLine;

	AudioInputStream audioInputStream;

	BufferedOutputStream out = null;

	BufferedInputStream in = null;

	Socket sock = null;
        DataOutputStream dos;
        DataInputStream dis;
        int flag=1;

	SourceDataLine sourceDataLine;
class ThreadCon extends Thread
{
Socket sock1=null;
ThreadCon(Socket sock1)
{
this.sock1=sock1;
}
public void run()
{

		try {
                       
                       out = new BufferedOutputStream(sock1.getOutputStream());
			in = new BufferedInputStream(sock1.getInputStream());
                         
			Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
			//txArea.append("Available mixers:\n");
System.out.println("Available mixers:\n");
			for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
				//txArea.append(mixerInfo[cnt].getName() + "\n");
System.out.println(mixerInfo[cnt].getName() + "\n");
			}
			audioFormat = getAudioFormat();

			DataLine.Info dataLineInfo = new DataLine.Info(
					TargetDataLine.class, audioFormat);

			Mixer mixer = AudioSystem.getMixer(mixerInfo[3]);

			targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);

			targetDataLine.open(audioFormat);
			targetDataLine.start();

			Thread captureThread = new CaptureThread();
			captureThread.start();

			DataLine.Info dataLineInfo1 = new DataLine.Info(
					SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem
					.getLine(dataLineInfo1);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			/*Thread playThread = new PlayThread();
			playThread.start();*/

		} catch (Exception e) {
		   System.out.println("Host not found!!\n");	
                  //txArea.append("Host not found!\n");
			
		}
	
}

}	class CaptureThread extends Thread {

		byte tempBuffer[] = new byte[1000];

		public void run() {
			byteArrayOutputStream = new ByteArrayOutputStream();
			stopCapture = false;
			try {
				while (!stopCapture) {

					int cnt = targetDataLine.read(tempBuffer, 0,
							tempBuffer.length);

					out.write(tempBuffer);

					if (cnt > 0) {

						byteArrayOutputStream.write(tempBuffer, 0, cnt);

					}
				}
				byteArrayOutputStream.close();
			} catch (Exception e) {
				//txArea.append("Exception");
				System.out.println("Exception");
			}
		}
	}

	public AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;

		int sampleSizeInBits = 8;

		int channels = 1;

		boolean signed = true;

		boolean bigEndian = false;

		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
				bigEndian);
	}



public Voip()
{
initComponents();
}

public void  initComponents()
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

//main panel(center)

	pp=new JPanel();
	add(pp,BorderLayout.CENTER);
	

//welcome panel

        p1=new Bg1Panel();
	p1.setLayout(null);
        
	tf2=new JTextField(30);
	tf2.setBackground(Color.cyan);
	tf2.setForeground(Color.black);

	i13=new ImageIcon("voipimages4.gif");
	nextb=new JButton("Next");
	quitb=new JButton("Quit");
	//nextb.setPreferredSize(new Dimension(70,100));
	//nextb.setIcon(i13);
	jl1 = new JLabel("Enter your name");
        jl1.setBounds(250,30,200,100);
        jl1.setFont(new Font("Comic Sans MS",10,18));
        tf2.setBounds(400,65,200,30);
        nextb.setBounds(340,480,60,40);	
	quitb.setBounds(420,480,60,40);	
	i1=new ImageIcon("output_13mwqCS.gif");
	imgb1=new JButton(i1);
        imgb1.setBounds(150,8,200,40);
	imgb1.setPreferredSize(new Dimension(300,240));
	//imgb1.setIcon(i1);
        jl15=new JLabel(" W e l c o m e ");
        jl15.setBounds(300,4,200,50);
	jl15.setFont(new Font("Monototype Corsiva",10,30));
	p1.add(jl1);
	p1.add(tf2);
	 
	p1.add(jl15);
	p1.add(nextb);
	p1.add(quitb);
		

	




/*tf2=new JTextField(30);
	nextb=new JButton("Next");
	jl1 = new JLabel("Enter your name");
	i1=new ImageIcon("f.jpg");
	imgb1=new JButton();
	imgb1.setPreferredSize(new Dimension(300,240));
	imgb1.setIcon(i1);
	p1=new BgPanel();
	p1.add(jl1);
	 p1.add(tf2);
	p1.add(imgb1);
	p1.add(nextb);
*/	

//choice panel
	
	p2=new Bg2Panel();
	p2.setLayout(null);
        jl2=new JLabel();
	jl4 = new JLabel("Welcome");
       	jl4.setFont(new Font("Monotype Corsiva",80,80));
	jl4.setBounds(100,40,300,80);
	jl2.setBounds(400,40,350,80);
	jl2.setFont(new Font("Monotype Corsiva",80,80));
	jl8=new JLabel(" to ");
	jl8.setFont(new Font("Monotype Corsiva",30,30));
        jl8.setBounds(350,130,80,30);
        jl19= new JLabel("!! VOIP !!");
        jl19.setBounds(290,190,300,50); 
        jl19.setFont(new Font("Monotype Corsiva",50,50));
	/*jl4 = new JLabel("     Welcome  ");
	jl4.setFont(new Font("Verdana",1,20));
	jl2.setFont(new Font("Verdana",1,20));*/
	

	i2=new ImageIcon("voipimage20.jpg");
	i3=new ImageIcon("voipimage22.png");
	achat=new JButton(i3);
        achat.setBounds(180,330,130,130);
	//achat=new JButton("Audio chat");
	//achat.setPreferredSize(new Dimension(70,50));
	mchat=new JButton(i2);
	mchat.setBounds(480,330,130,130);
	//mchat.setPreferredSize(new Dimension(70,50));
	imgb2=new JButton();
	//i2=new ImageIcon("y.jpg");
	imgb2.setPreferredSize(new Dimension(270,200));
	imgb2.setIcon(i2);
        jl10=new JLabel("Audio Chat");
        jl10.setFont(new Font("Comic Sans MS",10,15));
	jl10.setBounds(220,460,150,20);
        jl11=new JLabel("Message Chat");
        jl11.setFont(new Font("Comic Sans MS",10,15));
	jl11.setBounds(500,460,150,20);	
        i11=new ImageIcon("output_F4YFO1.gif");
        imgb4= new JButton(i11);
        imgb4.setBounds(360,360,100,100);
        back4=new JButton("Back");
        back4.setBounds(650,490,90,40);
	p2.add(jl4);
	p2.add(jl2);
	p2.add(achat);
	p2.add(mchat);
//	p2.add(imgb2);  
	p2.add(jl8);
        p2.add(jl10);
	p2.add(jl11);
 	p2.add(imgb4);
        p2.add(back4);
	p2.add(jl19);
 //audio chat panel
	
	p3=new Bg3Panel();  
        p3.setLayout(null);
	i6=new ImageIcon("voipimage16.png");
	discb=new JButton(i6);
	discb.setBounds(30,250,130,130);
	discb.setPreferredSize(new Dimension(70,50));
	tf1=new JTextField(15);
	tf1.setBounds(200,50,200,20);
        //tf1.setBackground(Color.yellow);
	//tf1.setForeground(Color.black);
 	i5=new ImageIcon("voipimage15.jpg");
        callb=new JButton(i5);
	callb.setBounds(300,250,130,130);
	callb.setPreferredSize(new Dimension(70,50));
	back1=new JButton("Quit");
	back1.setBounds(620,480,90,40);
        tf3=new JTextField(15);
	tf3.setBounds(200,90,200,20);
//tf3.setBackground(Color.yellow);
	//tf3.setForeground(Color.black);
        gl=new GridLayout(3,3);
	p31=new JPanel();
 	p31.setLayout(gl);
        i4=new ImageIcon("0001.gif");
	jl6=new JLabel();
        i7= new ImageIcon("voipimage18.gif");
        imgb4=new JButton();
        imgb4.setIcon(i7);
        
        //p31.setBackground(Color.red);
	i14=new ImageIcon("11.png");
	b1=new JButton("1");
	b2=new JButton("2");
 	b3=new JButton("3");
	b4=new JButton("4");
	b5=new JButton("5");
	b6=new JButton("6");
	b7=new JButton("7");
	b8=new JButton("8");
	b9=new JButton("9");
	b0=new JButton("0");
	b10=new JButton(".");
	b11=new JButton(i14);
	
        cb=new JComboBox();
        cb.addItem("--- Select here ---");
        cb.addItem("C-100");
        cb.addItem("C-101");
        cb.addItem("C-102");
        cb.addItem("C-103"); 
        cb.addItem("C-104");
        cb.addItem("C-105");
        cb.addItem("C-106");
        cb.addItem("C-107");
        cb.addItem("C-108");
        cb.addItem("C-109");
        cb.setBounds(500,50,200,20);
	
        p31.add(b1);
	p31.add(b2);
	p31.add(b3);
	p31.add(b4);
	p31.add(b5);
	p31.add(b6);
	p31.add(b7);
	p31.add(b8);
	p31.add(b11);
	p31.add(b9);
	p31.add(b0);
	p31.add(b10);
	p31.setPreferredSize(new Dimension(240,240));
	jl3 = new JLabel("Enter Recipient's Name");
	jl3.setFont(new Font("Verdana",15,15));
	jl3.setBounds(20,30,400,60);
        jl5=new JLabel("IP Address");
        jl5.setFont(new Font("Verdana",15,15));
	jl5.setBounds(20,70,400,60);	
        
        p3.add(jl3);
	p3.add(tf1);
	p3.add(callb);
	p3.add(discb);	
	p3.add(p31);
	p3.add(back1);
	p3.add(cb);
        p3.add(jl5);
        p3.add(tf3);        
        p3.add(jl6);
        p3.add(imgb4);
    	 p31.setBounds(470,200,250,250);     
        p3.add(p31);
        p3.add(cb);	
       

        p5=new Bg5Panel();
	p5.setLayout(null);
	jl16=new JLabel("Calling... ");
        jl16.setBounds(50,10,200,30);
        jl16.setFont(new Font("Monotype Corsiva",80,80));
	jl17=new JLabel();
        jl17.setBounds(100,40,200,30);
        jl17.setFont(new Font("Monotype Corsiva",80,80));
	i8=new ImageIcon("telephone4.gif");
        acptb=new JButton(i8);
        //acptb.setBounds(150,250,30,30);
        i9=new ImageIcon("voipimage25.jpg");
	rjctb=new JButton(i9);
	rjctb.setBounds(300,250,30,30);     
        jl12=new JLabel("Accept");
        jl12.setBounds(0,0,0,0);
        jl13=new JLabel("Reject");
        jl13.setBounds(0,0,0,0);

        /*acptb=new JButton("Accept");
	rjctb=new JButton("Reject");*/
	//holdb=new JButton("Hold");
//	p5.add(acptb);
//	p5.add(rjctb);
	//p5.add(holdb);
//	p5.add(jl12);
//	p5.add(jl13);
//	p5.add(jl16);
 //       p5.add(jl17);

//message welcome panel

p6=new Bg4Panel();
p6.setLayout(null);
jl7 = new JLabel("Enter Recipient's Name");
jl7.setFont(new Font("Verdana",15,15));
jl7.setBounds(10,100,400,60);
tf4=new JTextField(30);
tf4.setFont(new Font("Verdana",15,15));
tf4.setBounds(200,120,200,30);
tf4.setBackground(Color.yellow);
	tf4.setForeground(Color.black);
jl14=new JLabel("IP Address");
jl14.setBounds(10,160,400,60);
jl14.setFont(new Font("Verdana",15,15));
tf5=new JTextField(15);
tf5.setBounds(200,180,200,30);
tf5.setBackground(Color.yellow);
	tf5.setForeground(Color.black);

cb1=new JComboBox();

        cb1.addItem("--- Select here ---");
        cb1.addItem("C-100");
        cb1.addItem("C-101");
        cb1.addItem("C-102");
        cb1.addItem("C-103"); 
        cb1.addItem("C-104");
        cb1.addItem("C-105");
        cb1.addItem("C-106");
        cb1.addItem("C-107");
        cb1.addItem("C-108");
        cb1.addItem("C-109");
	cb1.setBounds(500,120,200,30);
i10=new ImageIcon("output_hURzke.gif");
conb=new JButton(i10);
conb.setBounds(260,300,200,140);
back3=new JButton("Back");
back3.setBounds(580,480,70,50);
p6.add(jl7);
p6.add(tf4);
p6.add(conb);
p6.add(back3);
p6.add(jl14);
p6.add(tf5);
p6.add(cb1);
//message panel
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
	i12=new ImageIcon("voipimage32.jpg");
	sendb=new JButton(i12);
	sendb.setBounds(590,360,100,100);
        back2=new JButton("Quit");
	back2.setBounds(670,480,70,50);


JScrollPane scr = new JScrollPane(ta2,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);// Add your text area to scroll pane 
        //ta1.setBounds(5, 35, 385, 330);
        //ta2.setLineWrap(true);
        //ta2.setWrapStyleWord(true);
        scr.setBounds(35, 30, 690, 270);
        p4.add(scr);
	p4.add(ta1);
	p4.add(sendb);
	p4.add(back2);


//adding p1,p2,p3 to main panel
	cl=new CardLayout();
	pp.setLayout(cl);
	pp.add(p1,"FIRST");
	pp.add(p2,"SECOND");
	pp.add(p3,"THIRD");
	pp.add(p6,"FOURTH");
        pp.add(p4,"FIFTH");

nextb.addActionListener(this);
achat.addActionListener(this);
mchat.addActionListener(this);
callb.addActionListener(this);
discb.addActionListener(this);
sendb.addActionListener(this);
conb.addActionListener(this);
b0.addActionListener(this);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
b7.addActionListener(this);
b8.addActionListener(this);
b9.addActionListener(this);
b10.addActionListener(this);
b11.addActionListener(this);
back1.addActionListener(this);
back2.addActionListener(this);
cb.addItemListener(this);
back3.addActionListener(this);
back4.addActionListener(this);  
quitb.addActionListener(this);                                                  
//ta2.addTextListener(this);
cb1.addItemListener(this);

}

public void textValueChanged(TextEvent te)
		{
			
			if(ta2.getText().endsWith("\n"))
			{
				imsg=ta1.getText();
				ta2.append("Client :  "+imsg);
				ta1.setText("");
				if(flg)
				{
					readdat();
				}
			}
		}

public void actionPerformed(ActionEvent e)
 {

//String s=e.getActionCommand();


if (e.getSource() == nextb)
   	{
	 this.s4=tf2.getText();
         cl.show(pp,"SECOND");
	jl2.setText(s4);
	jl17.setText(s4);
 	
	
   	}
if (e.getSource() == quitb)
   	{
         System.exit(0);
   	}



if (e.getSource() == achat)
   	{
        
         
         cl.show(pp,"THIRD");
URL url = PlayAlarm.class.getResource("key tone.wav"); 
         click2 = JApplet.newAudioClip(url);
  	 }

if (e.getSource() == back1)
   	{
        //click1.stop();
	tf1.setText(""); 
	tf3.setText("");
System.exit(0);
        /*cl.show(pp,"SECOND");
	try{

sock.close();
}
catch(Exception e3)
{
System.out.println(e3);
}*/
}
  	 
if (e.getSource() == back4)
   	{
        
        cl.show(pp,"FIRST");
  	 }



if (e.getSource() == mchat)
   	{
         cl.show(pp,"FOURTH");
   	}

if (e.getSource() == back2)
   	{
	ta1.setText(""); 
	ta2.setText("");
System.exit(0);
         //cl.show(pp,"SECOND");
          	
 }
if (e.getSource() == conb)
{
try{
 String host = tf3.getText();
sock = new Socket(host, 7700);

    
dos=new DataOutputStream(sock.getOutputStream());
  dis=new DataInputStream(sock.getInputStream());
dos.write(2);
cl.show(pp,"FIFTH");
Thread Connect=new Connect();
Connect.start();


//bin=new BufferedReader(new InputStreamReader(sock.getInputStream()));
String input;
  

}
catch(Exception e3)
{
System.out.println(e3);
}
}

if (e.getSource() == back3)
   	{
	tf4.setText(""); 
	tf5.setText("");
	cl.show(pp,"SECOND");
try{

sock.close();
}
catch(Exception e3)
{
System.out.println(e3);
}
}
          	
 



if (e.getSource() == callb)
{   
//URL urlClick = PlayAlarm.class.getResource("1.wav");
//click1 = JApplet.newAudioClip(urlClick);
//click1.loop();
//jl6.setIcon(i4);
    try{
  String host = tf3.getText();
sock = new Socket(host, 7700);


                       dos=new DataOutputStream(sock.getOutputStream());
                        dis=new DataInputStream(sock.getInputStream());

/*ops=sock.getOutputStream();
  ips=sock.getInputStream();*/

      dos.write(flag);
int flagcall=dis.read();
if (flagcall==1)
{
     //click1.stop(); 
      Thread ThreadCon=new ThreadCon(sock);
      ThreadCon.start();
     

 }
else

{
click1.stop();
System.exit(0);
}
}
catch(Exception e1)
{
System.out.println(e1);
}
}

if (e.getSource() == sendb)
   	{


try{
  
//String input;
System.out.println(this.s4);

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
  
if (e.getSource() == discb)
   	{
         
          
try{
//dos.write(1);
//click1.stop();
cl.show(pp,"THIRD");	
jl6.setIcon(null);
sock.close();

//int ind=dis.read();
//if(ind==1)

}
catch(Exception e3)
{
System.out.println(e3);
}
}

if (e.getSource() == b0)
   	{
         ip=ip+"0";
         tf3.setText(ip);
   	click2.play();
        }
  

if (e.getSource() == b1)
   	{    
         
        ip=ip+"1";
         tf3.setText(ip);
        click2.play(); 
  	}
  

if (e.getSource() == b2)
   	{
        ip=ip+"2";
         tf3.setText(ip);
         click2.play();
   	}
  

if (e.getSource() == b3)
   	{
         ip=ip+"3";
         tf3.setText(ip);
   	click2.play();
        }
  

if (e.getSource() == b4)
   	{
         ip=ip+"4";
         tf3.setText(ip);    
         click2.play();

   	}
  

if (e.getSource() == b5)
   	{
        ip=ip+"5";
         tf3.setText(ip);
        click2.play();
   	}
  

if (e.getSource() == b6)
   	{
      ip=ip+"6";
         tf3.setText(ip);
        click2.play();
   	}
  

if (e.getSource() == b7)
   	{
         ip=ip+"7";
         tf3.setText(ip);
         click2.play();
   	}
  

if (e.getSource() == b8)
   	{
        ip=ip+"8";
         tf3.setText(ip);
         click2.play();
    	}
  

if (e.getSource() == b9)
   	{
         ip=ip+"9";
         tf3.setText(ip);
         click2.play();
   	}
  

if (e.getSource() == b10)
   	{
         ip=ip+".";
         tf3.setText(ip);
         click2.play();
   	}


if (e.getSource() == b11)
   	{
         //tf3.setText("/");
	ip = ip.substring(0, ip.length() - 1);
	 tf3.setText(ip);
         click2.play();
   	}  

if (e.getSource() == acptb)
   	{
         //tf1.setText("10");
   	}

if (e.getSource() == rjctb)
   	{
         //tf1.setText("10");
   	}

if (e.getSource() == holdb)
   	{
         //stf1.setText("10");
   	}
  }


void readdat()
		{
			try

			{System.out.println("Hello");
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
                               while(true)
				{
					try
					{
						//bt=new byte[100];
String st;
System.out.println("Message3");
						st=dis.readUTF();
System.out.println("Message3");
						ta2.append("\nServer:  "+st+"\n");
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


public void itemStateChanged(ItemEvent ie)
{

if("--- Select here ---".equals(cb.getSelectedItem()))
{
tf1.setText("Enter Recepient");
tf3.setText("Enter Recepient's IP Address");
}

if("C-100".equals(cb.getSelectedItem()))
         {
tf1.setText("C-100");
tf3.setText("127.5.5.5");
}
if("C-101".equals(cb.getSelectedItem()))
{
tf1.setText("C-101");
tf3.setText("4,4.3.3");
}
if("C-102".equals(cb.getSelectedItem()))
{
tf1.setText("C-102");
tf3.setText("12fdsf5");
}
if("C-103".equals(cb.getSelectedItem()))
{
tf1.setText("C-102");
tf3.setText("127.5.5.5");
}
if("C-103".equals(cb.getSelectedItem()))
{
tf1.setText("C-103");
tf3.setText("127.5.5.5");
}
if("C-104".equals(cb.getSelectedItem()))
{
tf1.setText("C-104");
tf3.setText("125.5.5");
}
if("C-105".equals(cb.getSelectedItem()))
{
tf1.setText("C-105");
tf3.setText("127.5.5.5");
}
if("C-106".equals(cb.getSelectedItem()))
{
tf1.setText("C-106");
tf3.setText("127.5.5.5");
}
if("C-107".equals(cb.getSelectedItem()))
{
tf1.setText("C-107");
tf3.setText("127.5.5.5");
}
if("C-108".equals(cb.getSelectedItem()))
{
tf1.setText("C-108");
tf3.setText("127.5.5.5");
}
if("C-109".equals(cb.getSelectedItem()))
{
tf1.setText("C-109");
tf3.setText("127.5.5.5");
}




//public void itemStateChanged(ItemEvent ie1)



if("--- Select here ---".equals(cb1.getSelectedItem()))
 {
tf4.setText("Enter Recepient");
tf5.setText("Enter Recepient's IP Address");
}

if("C-100".equals(cb1.getSelectedItem()))
         {
tf4.setText("C-100");
tf5.setText("127.5.5.5");
}
if("C-101".equals(cb1.getSelectedItem()))
{
tf4.setText("C-101");
tf5.setText("4,4.3.3");
}
if("C-102".equals(cb1.getSelectedItem()))
{
tf4.setText("C-102");
tf5.setText("12fdsf5");
}
if("C-103".equals(cb1.getSelectedItem()))
{
tf4.setText("C-102");
tf5.setText("127.5.5.5");
}
if("C-103".equals(cb1.getSelectedItem()))
{
tf4.setText("C-103");
tf5.setText("127.5.5.5");
}
if("C-104".equals(cb1.getSelectedItem()))
{
tf4.setText("C-104");
tf5.setText("125.5.5");
}
if("C-105".equals(cb1.getSelectedItem()))
{
tf4.setText("C-105");
tf5.setText("127.5.5.5");
}
if("C-106".equals(cb1.getSelectedItem()))
{
tf4.setText("C-106");
tf5.setText("127.5.5.5");
}
if("C-107".equals(cb1.getSelectedItem()))
{
tf4.setText("C-107");
tf5.setText("127.5.5.5");
}
if("C-108".equals(cb1.getSelectedItem()))
{
tf4.setText("C-108");
tf5.setText("127.5.5.5");
}
if("C-109".equals(cb1.getSelectedItem()))
{
tf4.setText("C-109");
tf5.setText("127.5.5.5");
}

}
}




 	class Bg1Panel extends JPanel {
    Image bg = new ImageIcon("tytd.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}


class Bg2Panel extends JPanel {
    Image bg = new ImageIcon("background1.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}


class Bg3Panel extends JPanel {
    Image bg = new ImageIcon("background17.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

class Bg5Panel extends JPanel {
    Image bg = new ImageIcon("background12.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}


class Bg4Panel extends JPanel {
    Image bg = new ImageIcon("background19.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}





class trap
{

public static void main(String s[])
{
Voip vv=new Voip();
vv.setTitle("--Voip--");
vv.setSize(800,600);
vv.setVisible(true);
vv.setResizable(false);

}
}