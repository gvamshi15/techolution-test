package com.techholution.domain;


public class ItemPojo {
	
	private Integer satisfaction;
	private Integer timeTaken;
	
	/**
	 * @return the satisfaction
	 */
	public Integer getSatisfaction() {
		return satisfaction;
	}
	
	/**
	 * @param satisfaction the satisfaction to set
	 */
	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}
	
	/**
	 * @return the timeTaken
	 */
	public Integer getTimeTaken() {
		return timeTaken;
	}
	
	/**
	 * @param timeTaken the timeTaken to set
	 */
	public void setTimeTaken(Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [satisfaction=" + satisfaction + ", timeTaken=" + timeTaken + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((satisfaction == null) ? 0 : satisfaction.hashCode());
		result = prime * result + ((timeTaken == null) ? 0 : timeTaken.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPojo other = (ItemPojo) obj;
		if (satisfaction == null) {
			if (other.satisfaction != null)
				return false;
		} else if (!satisfaction.equals(other.satisfaction))
			return false;
		if (timeTaken == null) {
			if (other.timeTaken != null)
				return false;
		} else if (!timeTaken.equals(other.timeTaken))
			return false;
		return true;
	}

	public ItemPojo() {
		super();
	}

	public ItemPojo(Integer satisfaction, Integer timeTaken) {
		super();
		this.satisfaction = satisfaction;
		this.timeTaken = timeTaken;
	}

}
