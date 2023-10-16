package labs.lab9;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.JList;
import javax.swing.JMenu;


import javax.swing.border.TitledBorder;


import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Choice;

import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup priorityGroup = new ButtonGroup();
	private JLabel userLabel;
	private JButton sendButton;
	private JButton clearButton;
	private JTextArea textBox;
	private JTextArea subjectText;
	private Choice userDropdown;
	private JRadioButton lowPriority;
	private JRadioButton medPriority;
	private JRadioButton highPriority;
	private JList<String> received;
	private JMenuItem switchUser;
	private JLayeredPane inboxPanel;
	private JTextArea message;
	private JMenuItem exit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Email System - Quincy Hunter - 26862148");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 750);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		 
        // create menuitems
        exit = new JMenuItem("Exit");
        file.add(exit);
        menuBar.add(file);
        JMenu userMenu = new JMenu("Users");
        switchUser = new JMenuItem("Switch User");
        userMenu.add(switchUser);
        menuBar.add(userMenu);
		menuBar.setForeground(new Color(0, 0, 0));
		menuBar.setBackground(new Color(240, 240, 240));
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userLabel = new JLabel("Current User: ");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setBounds(251, 11, 281, 21);
		contentPane.add(userLabel);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(485, 410, -485, -380);
		contentPane.add(layeredPane);
		
		inboxPanel = new JLayeredPane();
		inboxPanel.setBorder(new TitledBorder(null, "Inbox", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		inboxPanel.setBounds(46, 34, 697, 222);
		contentPane.add(inboxPanel);
		
		received = new JList<String>();
		received.setBounds(10, 24, 336, 188);
		inboxPanel.add(received);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 24, 335, 188);
		inboxPanel.add(scrollPane);
		
		message = new JTextArea();
		scrollPane.setViewportView(message);
		message.setEditable(false);
		
		JLayeredPane messagePanel = new JLayeredPane();
		messagePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "New Message", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		messagePanel.setBounds(43, 267, 700, 411);
		contentPane.add(messagePanel);
		
		JScrollPane textBoxPane = new JScrollPane();
		textBoxPane.setBounds(10, 170, 680, 200);
		messagePanel.add(textBoxPane);
		
		textBox = new JTextArea();
		textBoxPane.setViewportView(textBox);
		
		sendButton = new JButton("Send");
		sendButton.setBounds(255, 381, 85, 23);
		messagePanel.add(sendButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(351, 381, 85, 23);
		messagePanel.add(clearButton);
		
		JLabel toLabel = new JLabel("To:");
		toLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toLabel.setBounds(258, 23, 23, 20);
		messagePanel.add(toLabel);
		
		userDropdown = new Choice();
		userDropdown.add("Robert Navarro");
		userDropdown.setBounds(286, 23, 150, 20);
		messagePanel.add(userDropdown);
		
		subjectText = new JTextArea();
		subjectText.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		subjectText.setBounds(255, 91, 239, 30);
		messagePanel.add(subjectText);
		
		JLabel subjectLabel = new JLabel("Subject: ");
		subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subjectLabel.setBounds(185, 93, 60, 30);
		messagePanel.add(subjectLabel);
		
		JLabel priorityLabel = new JLabel("Priority: ");
		priorityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priorityLabel.setBounds(216, 60, 65, 23);
		messagePanel.add(priorityLabel);
		
		highPriority = new JRadioButton("High");
		highPriority.setFont(new Font("Tahoma", Font.PLAIN, 14));
		highPriority.setSelected(true);
		priorityGroup.add(highPriority);
		highPriority.setBounds(269, 61, 52, 20);
		messagePanel.add(highPriority);
		
		medPriority = new JRadioButton("Medium");
		medPriority.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priorityGroup.add(medPriority);
		medPriority.setBounds(334, 61, 85, 20);
		messagePanel.add(medPriority);
		
		lowPriority = new JRadioButton("Low");
		lowPriority.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priorityGroup.add(lowPriority);
		lowPriority.setBounds(421, 61, 60, 20);
		messagePanel.add(lowPriority);
		
		
	}
	public void setCurrentUser(String name, JLabel label)
	{
		label.setText("Current User: " +name);
	}
	public JLabel getUserLabel()
	{
		return userLabel;
	}
	public JButton getSendButton()
	{
		return sendButton;
	}
	public JButton getClearButton()
	{
		return clearButton;
	}
	public JTextArea getTextBox()
	{
		return textBox;
	}
	public JTextArea getSubjectText()
	{
		return subjectText;
	}
	public Choice getRecipient()
	{
		return userDropdown;
	}
	public int getPriority()
	{
		if(lowPriority.isSelected() == true)
		{
			return 1;
		}
		else if(medPriority.isSelected() == true)
		{
			return 2;
		}
		else
		{
			return 3;
		}
	}
	public void setHigh()
	{
		medPriority.setSelected(false);
		highPriority.setSelected(true);
	}
	
	public void setList(DefaultListModel<String> list)
	{
		received.setModel(list);
		inboxPanel.repaint();
		inboxPanel.revalidate();
		
	}
	public void clearList()
	{
		DefaultListModel<String> list = new DefaultListModel<String>();
		received.setModel(list);
		inboxPanel.repaint();
		inboxPanel.revalidate();
	}
	public JList<String> getList()
	{
		return received;
	}
	public JMenuItem getSwitch()
	{
		return switchUser;
	}
	public JMenuItem getExit()
	{
		return exit;
	}
	public void setMessage(String text)
	{
		message.setText(text);
		message.setCaretPosition(0);
	}
}
