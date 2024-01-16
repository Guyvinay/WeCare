package com.weCare.modals;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String profile_id;

	@NotBlank(message = "Email cannot be blank!!!")
	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email should be in proper format i.e : johndoe@example.com")
	private String email;

	@NotBlank(message = "Password cannot be blank!!!")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String passWord;

	private String profile_picture;

	@NotNull(message = "Role cannot be blank!!!")
	@Enumerated(EnumType.STRING)
	private Role role;

	public Profile(String email, String passWord, String profile_picture, Role role) {
		super();
		this.email = email;
		this.passWord = passWord;
		this.profile_picture = profile_picture;
		this.role = role;
	}

}
