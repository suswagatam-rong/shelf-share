package org.kai.shelfshare.auth;
/*
 * @created 25/06/2024 - 09:32
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import lombok.RequiredArgsConstructor;
import org.kai.shelfshare.role.RoleRepository;
import org.kai.shelfshare.user.Token;
import org.kai.shelfshare.user.TokenRepository;
import org.kai.shelfshare.user.User;
import org.kai.shelfshare.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;

	public void register(RegistrationRequest request) {

		var userRole = roleRepository.findByName("USER")
				.orElseThrow(() -> new IllegalStateException("Role USER not initialised")); // TODO: Implement better Exception Handling

		var user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.accountLocked(false)
				.enabled(false)
				.roles(List.of(userRole))
				.build();

		userRepository.save(user);
		sendValidationEmail(user);
	}

	private void sendValidationEmail(User user) {

		var newToken = generateAndSaveActivationToken(user);
		// TODO: Send Email

	}

	private String generateAndSaveActivationToken(User user) {
		// Generate Token
		int tokenLength = 6;
		String generatedToken = generateActivationCode(tokenLength);

		var token = Token.builder()
				.token(generatedToken)
				.createdAt(LocalDateTime.now())
				.expiresAt(LocalDateTime.now().plusMinutes(15))
				.user(user)
				.build();

		tokenRepository.save(token);
		return generatedToken;
	}

	private String generateActivationCode(int length) {
		String characters = "0123456789";
		StringBuilder codeBuilder = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int randomIndex = secureRandom.nextInt(characters.length()); // 0..9
			codeBuilder.append(characters.charAt(randomIndex));
		}

		return codeBuilder.toString();
	}
}