package com.danplay.server.match.application;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.match.domain.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;

	public Match registerMatch(Match match) {
		return matchRepository.save(match);
	}
}
