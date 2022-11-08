package club.superk.hueacm.matchingsystem.service.impl;

import club.superk.hueacm.matchingsystem.service.MatchingService;
import club.superk.hueacm.matchingsystem.service.impl.utils.MatchingPool;
import org.springframework.stereotype.Service;

@Service
public class MatchingServiceImpl implements MatchingService {
    public final static MatchingPool matchingPool = new MatchingPool();

    @Override
    public String addPlayer(Integer userId, Integer rating, Integer botId) {
        matchingPool.addPlayer(userId, rating, botId);
        System.out.println("add player: " + userId + " " + rating);
        return "add player success";
    }

    @Override
    public String removePlayer(Integer userId) {
        matchingPool.removePlayer(userId);
        System.out.println("remove player: " + userId);
        return "remove player success";
    }
}
