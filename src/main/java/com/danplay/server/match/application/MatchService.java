package com.danplay.server.match.application;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.match.domain.repository.MatchRepository;
import com.danplay.server.match.dto.MatchRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;

	public void registerMatch(Match match) {
		matchRepository.save(match);
	}

	@Transactional(readOnly = true)
	public List<Match> findMatches() {
		return matchRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Match findMatchById(Long matchId) throws Exception {
		return matchRepository.findById(matchId).orElseThrow(Exception::new);
	}

	public void removeMatchById(Long matchId) {
		matchRepository.deleteById(matchId);
	}

	public void updateMatch(Match match, MatchRequest matchRequest) {
		match.updateMatch(matchRequest);
	}
}
