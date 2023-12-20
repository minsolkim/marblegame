package Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class ClientMain extends JFrame {
   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField txtUserName;
   private JTextField txtIpAddress;
   private JTextField txtPortNumber;
   
   private MyPanel panel=new MyPanel();

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               ClientMain frame = new ClientMain();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   
   class MyPanel extends JPanel{
      private ImageIcon icon=new ImageIcon("./Start.png");
      private Image img=icon.getImage();
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(img,0,0,getWidth(),getHeight(),this);
      }
   }

   /**
    * Create the frame.
    */
   public ClientMain() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 520, 450);
      contentPane = new JPanel();
      setContentPane(panel);
      panel.setBorder(new EmptyBorder(5, 5, 5, 5));
      //setContentPane(contentPane);
      panel.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("   ID를 입력해주세요 !!   ");
      lblNewLabel.setBounds(200, 19, 128, 33);
      lblNewLabel.setOpaque(true);
      lblNewLabel.setBackground(Color.WHITE);
      panel.add(lblNewLabel);
      
      txtUserName = new JTextField();
      txtUserName.setHorizontalAlignment(SwingConstants.CENTER);
      txtUserName.setBounds(205, 69, 116, 33);
      panel.add(txtUserName);
      txtUserName.setColumns(30);
      
      JLabel lblIpAddress = new JLabel("IP Address");
      lblIpAddress.setBounds(12, 100, 82, 33);
      contentPane.add(lblIpAddress);
      
      txtIpAddress = new JTextField();
      txtIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
      txtIpAddress.setText("127.0.0.1");
      txtIpAddress.setColumns(10);
      txtIpAddress.setBounds(101, 100, 116, 33);
      contentPane.add(txtIpAddress);
      
      JLabel lblPortNumber = new JLabel("Port Number");
      lblPortNumber.setBounds(12, 163, 82, 33);
      contentPane.add(lblPortNumber);
      
      txtPortNumber = new JTextField();
      txtPortNumber.setText("30000");
      txtPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
      txtPortNumber.setColumns(10);
      txtPortNumber.setBounds(101, 163, 116, 33);
      contentPane.add(txtPortNumber);
      
      JButton btnConnect = new JButton("로그인");
      btnConnect.setBounds(12, 223, 205, 38);
      contentPane.add(btnConnect);
      Myaction action = new Myaction();
      btnConnect.addActionListener(action);
      txtUserName.addActionListener(action);
      txtIpAddress.addActionListener(action);
      txtPortNumber.addActionListener(action);
      
      setVisible(true);
   }
   class Myaction implements ActionListener // 내부클래스로 액션 이벤트 처리 클래스
   {
      @Override
      public void actionPerformed(ActionEvent e) {
         String username = txtUserName.getText().trim();
         String ip_addr = txtIpAddress.getText().trim();
         String port_no = txtPortNumber.getText().trim();
         ClientView view = new ClientView(username, ip_addr, port_no);
         setVisible(false);
      }
   }
}
