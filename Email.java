package labs.lab9;

import java.time.*;

@SuppressWarnings("rawtypes")
public class Email implements Comparable 
{
	private String from;
	private int priority;
	private String subject;
	private String text;
	private LocalDate date;
	private LocalTime time;
	
	public Email(String from, int priority, String subject, String text)
	{
		this.from = from;
		this.priority = priority;
		this.subject = subject;
		this.text = text;
		this.date = LocalDate.now();
		this.time = LocalTime.now();
	}
	public String getText()
	{
		return this.text;
	}
	public int getPriority()
	{
		return this.priority;
	}
	public String getSubject()
	{
		return this.subject;
	}
	public String getFrom()
	{
		return this.from;
	}
	public String getDate()
	{
		return date.toString() + " "+time.toString().substring(0,time.toString().indexOf(":")+3);
	}
	public LocalDate getDay()
	{
		return this.date;
	}
	public LocalTime getTime()
	{
		return this.time;
	}
	@Override
	public int compareTo(Object o) {
		if(((Email) o).getPriority()-priority == 0)
		{
			if(this.date.compareTo(((Email) o).getDay()) == 0)
			{
				return ((Email) o).getTime().compareTo(this.time);
			}
			return this.date.compareTo(((Email) o).getDay());
		}
		else
		{
			return ((Email) o).getPriority()-priority;
		}
	}
}