package com.danplay.server.match.application;

import com.danplay.server.match.domain.entity.Match;
import com.danplay.server.match.domain.repository.MatchRepository;
import com.danplay.server.match.dto.MatchRequest;
import com.danplay.server.usermatch.domain.entity.UserMatch;
import java.util.List;

import com.danplay.server.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;

	public void registerMatch(Match match, User loginUser) {
		loginUser.setIsHost(true);
		setUserMatch(match, loginUser);
		matchRepository.save(match);
		List<Match> all = matchRepository.findAll();
		for (Match match1 : all) {
			List<UserMatch> participants = match1.getParticipants();
			for (UserMatch participant : participants) {
				User user = participant.getUser();
				System.out.println("user.toString( = " + user.toString());
			}
		}
	}

	private void setUserMatch(Match match, User loginUser) {
		UserMatch userMatch = new UserMatch();
		userMatch.setUser(loginUser);
		userMatch.setMatch(match);
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

//	public void testJWT(HttpServletRequest httpServletRequest) {
//
//		// 회원가입 후, 로그인을 했을 때 나오는 토큰으로 진행
//
//		// -- 테스트 --
//		// 토큰을 넣지 않았을때
//		// 토큰에 노이즈를 넣었을 때
//
//		// 다음을 통해 토큰으로 유저 획득
//		final User user = authService.getUserByToken(httpServletRequest);
//
//		// 유저 출력
//		System.out.println(user.getMail());
//		System.out.println(user.getName());
//	}
}
