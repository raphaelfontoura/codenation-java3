package challenge.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
	
	private Long id;
	private String fullName;
	private String club;
	private int age;
	private LocalDate birthDate;
	private String nationality;
	private BigDecimal eurWage;
	private BigDecimal eurRelClause;
	
	public Jogador(Long id, String fullName, String club, int age, LocalDate birthDate, String nationality,
			BigDecimal eurWage, BigDecimal eurRelClause) {
		this.id = id;
		this.fullName = fullName;
		this.club = club;
		this.age = age;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.eurWage = eurWage;
		this.eurRelClause = eurRelClause;
	}
	
	private Jogador(Builder builder) {
		id = builder.id;
		fullName = builder.fullName;
		club = builder.club;
		age = builder.age;
		birthDate = builder.birthDate;
		nationality = builder.nationality;
		eurWage = builder.eurWage;
		eurRelClause = builder.eurRelClause;
	}

	public static class Builder {
		
		private Long id;
		private String fullName;
		private String club;
		private int age;
		private LocalDate birthDate;
		private String nationality;
		private BigDecimal eurWage;
		private BigDecimal eurRelClause;
		
		public Builder(Long id) {
			this.id = id;
		}
		public Builder fullName(String fullName) {
			this.fullName = fullName;
			return this;
		}
		public Builder inClub(String club) {
			this.club = club;
			return this;
		}
		public Builder withAge(int age) {
			this.age = age;
			return this;
		}
		public Builder andBirthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}
		public Builder fromNationality(String nationality) {
			this.nationality = nationality;
			return this;
		}
		public Builder withEurWage(BigDecimal eurWage) {
			this.eurWage = eurWage;
			return this;
		}
		public Builder withEurReleaseClause(BigDecimal eurRelClause) {
			this.eurRelClause = eurRelClause;
			return this;
		}
		public Jogador build() {
			return new Jogador(this);
		}
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getClub() {
		return club;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public BigDecimal getEurWage() {
		return eurWage;
	}

	public BigDecimal getEurRelClause() {
		return eurRelClause;
	}

}
