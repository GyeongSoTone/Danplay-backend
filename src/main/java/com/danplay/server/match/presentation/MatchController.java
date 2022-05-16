package com.danplay.server.match.presentation;

import com.danplay.server.match.application.MatchService;
import com.danplay.server.match.dto.MatchRequest;
import com.danplay.server.match.dto.MatchResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/match")
public class MatchController {

	private final MatchService matchService;

	@PostMapping("/register")
	public ResponseEntity<HttpStatus> register(@RequestBody MatchRequest matchRequest) {
		matchService.registerMatch(matchRequest.toEntity());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<MatchResponse>> findMatches() {
		return ResponseEntity.ok()
			.body(matchService.findMatches().stream()
				.map(MatchResponse::of)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{matchId}")
	public ResponseEntity<MatchResponse> findMatch(@PathVariable Long matchId) throws Exception {
		return ResponseEntity.ok()
			.body(MatchResponse.of(matchService.findMatchById(matchId)));
	}
}
