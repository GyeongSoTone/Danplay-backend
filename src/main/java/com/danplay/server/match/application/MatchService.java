package com.danplay.server.match.application;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.match.domain.repository.MatchRepository;
import com.danplay.server.match.dto.MatchRequest;
import com.danplay.server.usermatch.domain.entity.UserMatch;
import com.danplay.server.usermatch.domain.repository.UserMatchRepository;
import java.util.List;

import com.danplay.server.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;
	private final UserMatchRepository userMatchRepository;

	public void registerMatch(Match match, User loginUser) {
		// TODO::현재 match에 대해서만 host 수정
		loginUser.setIsHost(true);
		setUserMatch(match, loginUser);
		matchRepository.save(match);
	}

	private void setUserMatch(Match match, User loginUser) {
		UserMatch userMatch = new UserMatch();
		userMatch.setUser(loginUser);
		userMatch.setMatch(match);
		userMatchRepository.save(userMatch);
	}

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
