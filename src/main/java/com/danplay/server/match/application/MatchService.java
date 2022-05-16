package com.danplay.server.match.application;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.match.domain.repository.MatchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;

	public Match registerMatch(Match match) {
		return matchRepository.save(match);
	}

	@Transactional(readOnly = true)
	public List<Match> findMatches() {
		return matchRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Match findMatchById(Long findMatchById) throws Exception {
		return matchRepository.findById(findMatchById).orElseThrow(Exception::new);
	}
}
