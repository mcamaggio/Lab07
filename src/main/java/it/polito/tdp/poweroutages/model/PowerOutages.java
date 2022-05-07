package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages implements Comparable <PowerOutages>{
	
	private int id;
	private Nerc nerc;
	private LocalDateTime outageStart;
	private LocalDateTime outageEnd;
	private int affectedPeople;
	private long outageDuration;
	private int year;
	
	public PowerOutages(int id, Nerc nerc, LocalDateTime outageStart, LocalDateTime outageEnd, int affectedPeople) {
		this.id = id;
		this.nerc = nerc;
		this.outageStart = outageStart;
		this.outageEnd = outageEnd;
		this.affectedPeople = affectedPeople;
		
		LocalDateTime tempDateTime = LocalDateTime.from(outageStart);
		this.outageDuration = tempDateTime.until(outageEnd, ChronoUnit.HOURS);
		this.year = outageStart.getYear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public LocalDateTime getOutageStart() {
		return outageStart;
	}

	public void setOutageStart(LocalDateTime outageStart) {
		this.outageStart = outageStart;
	}

	public LocalDateTime getOutageEnd() {
		return outageEnd;
	}

	public void setOutageEnd(LocalDateTime outageEnd) {
		this.outageEnd = outageEnd;
	}

	public int getAffectedPeople() {
		return affectedPeople;
	}

	public void setAffectedPeople(int affectedPeople) {
		this.affectedPeople = affectedPeople;
	}

	public long getOutageDuration() {
		return outageDuration;
	}

	public void setOutageDuration(long outageDuration) {
		this.outageDuration = outageDuration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(PowerOutages o) {
		return this.outageStart.compareTo(o.getOutageStart());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		return builder.toString();
	}
	
	

}
