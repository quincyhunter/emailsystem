package labs.lab9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class EmailSystem implements ActionListener
{
	private String currentUser = "";
	private ArrayList<String> users;
	private HashMap<String, ArrayList<Email>> userMessages;
	
	private MainFrame frame;
	public EmailSystem()
	{
		users = new ArrayList<String>();
		users.add("Robert Navarro");
		userMessages = new HashMap<String, ArrayList<Email>>();
		
		frame = new MainFrame();
		while(true)
		{
			String name = JOptionPane.showInputDialog(frame,"Enter your username:");
			if(name.isBlank() == false)
			{
				currentUser = name;
				 break;
			}
		}
		
		
		  
		  users.add(currentUser);
		  frame.setCurrentUser(currentUser, frame.getUserLabel());
		  frame.setLocationRelativeTo(null);
	      frame.setVisible(true);
	      frame.getClearButton().addActionListener(this);
	      frame.getSendButton().addActionListener(this);
	      frame.getExit().addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e)
	    	  {
	    		 frame.dispose(); 
	    	  }
	      });
	      frame.getSwitch().addActionListener(new ActionListener() {
	            @SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent e) {
	            	
	            	String name = JOptionPane.showInputDialog(frame,"Enter your username:");
	            	if(name != null)
	            	{
	            		currentUser = name;
	            		users.add(name);
	            		frame.setCurrentUser(currentUser, frame.getUserLabel());
	            		frame.getTextBox().setText("");
	        			frame.getSubjectText().setText("");
	        			frame.getRecipient().removeAll();
	        			frame.clearList();
	        			HashSet<String> sendNames = new HashSet<String>(users);
	        			for(String i : sendNames)
	        			{
	        				if(currentUser.equals(i) == false)
	        				{
	        					frame.getRecipient().add(i);
	        				}
	        				
	        			}
	            		DefaultListModel<String> listModel = new DefaultListModel<String>();
	            		if(userMessages.get(currentUser) != null)
	            		{
	            			Collections.sort(userMessages.get(currentUser));
	            		
	            		
		        			for(int i = 0; i < userMessages.get(currentUser).size(); i++)
		        			{
		        				listModel.addElement("From: "+userMessages.get(currentUser).get(i).getFrom()+", Subject: "+userMessages.get(currentUser).get(i).getSubject());
		        				
		        			}
		        			frame.setList(listModel);
	        			}
	            		
	            		
	            		frame.setMessage("");
	            		frame.setHigh();
	            	}
	            }
	      });
	      frame.getList().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

	            
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(frame.getList().getSelectedValue() != null)
					{
						String selected = frame.getList().getSelectedValue().toString();
						for(int i = 0; i < userMessages.get(currentUser).size(); i++)
						{
							String sample = "From: "+userMessages.get(currentUser).get(i).getFrom()+", Subject: "+userMessages.get(currentUser).get(i).getSubject();
							if(sample.equals(selected))
							{
								String message = "";
								message += "From: "+userMessages.get(currentUser).get(i).getFrom() + System.lineSeparator();
								message += "To: "+currentUser + System.lineSeparator();
								if(userMessages.get(currentUser).get(i).getPriority() == 1)
								{
									message += "Priority: Low" + System.lineSeparator();
								}
								if(userMessages.get(currentUser).get(i).getPriority() == 2)
								{
									message += "Priority: Medium" + System.lineSeparator();
								}
								if(userMessages.get(currentUser).get(i).getPriority() == 3)
								{
									message += "Priority: High" + System.lineSeparator();
								}
								message += "Subject: "+userMessages.get(currentUser).get(i).getSubject() + System.lineSeparator();
								message += userMessages.get(currentUser).get(i).getDate() + System.lineSeparator() + System.lineSeparator();
								message += userMessages.get(currentUser).get(i).getText();
								
								frame.setMessage(message);
							}
						}
					}
				}
					
	        });
		
	      
		
		
		 
		
	}
	
	public static void main(String[] args)
	{
		new EmailSystem();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().toString().equals("Clear"))
		{
			//clear messageText
			frame.getTextBox().setText("");
			frame.getSubjectText().setText("");
			frame.setHigh();
		}
		if(e.getActionCommand().toString().equals("Send") && frame.getSubjectText().getText().length() > 0)
		{
			Email mail = new Email(currentUser, frame.getPriority(), frame.getSubjectText().getText(), frame.getTextBox().getText());
			if(userMessages.containsKey(frame.getRecipient().getSelectedItem()))
			{
				userMessages.get(frame.getRecipient().getSelectedItem()).add(mail);
			}
			else
			{
				ArrayList<Email> mailList = new ArrayList<Email>();
				mailList.add(mail);
				userMessages.put(frame.getRecipient().getSelectedItem(), mailList);
			}
			
			/*
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			Collections.sort(userMessages.get(currentUser));
			for(int i = 0; i < userMessages.get(currentUser).size(); i++)
			{
				listModel.addElement("From: "+userMessages.get(currentUser).get(i).getFrom()+", Subject: "+userMessages.get(currentUser).get(i).getSubject());
			}
			frame.setList(listModel);
			*/
			String[] options = {"OK"};
			JOptionPane.showOptionDialog(frame, "Message sent ","Message", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options,  options[0]);
			frame.getTextBox().setText("");
			frame.getSubjectText().setText("");
			frame.setHigh();
		}
		
	}
}