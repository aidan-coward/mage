package mage.target.common;

import mage.abilities.Ability;
import mage.cards.Card;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.players.Player;
import mage.target.TargetCard;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author BetaSteward_at_googlemail.com
 */
public class TargetCardInHand extends TargetCard {

    public TargetCardInHand() {
        this(1, 1, new FilterCard());
    }

    public TargetCardInHand(FilterCard filter) {
        this(1, 1, filter);
    }

    public TargetCardInHand(int numTargets, FilterCard filter) {
        this(numTargets, numTargets, filter);
    }

    public TargetCardInHand(int minNumTargets, int maxNumTargets, FilterCard filter) {
        super(minNumTargets, maxNumTargets, Zone.HAND, filter);
        setNotTarget(true);
    }

    public TargetCardInHand(final TargetCardInHand target) {
        super(target);
    }

    @Override
    public boolean canTarget(UUID playerId, UUID id, Ability source, Game game) {
        // Has to be a card in the hand of a player in range. We don't know here, from which player's hand so we have to check all possible players
        // And because a card in hand is never targeted we can omitt specific targeting related checks
        return game.getState().getZone(id) == Zone.HAND 
                && game.getState().getPlayersInRange(getTargetController() == null ? playerId : getTargetController(), game).contains(game.getOwnerId(id));
    }

    @Override
    public boolean canTarget(UUID id, Ability source, Game game) {
        return this.canTarget(source.getControllerId(), id, source, game);
    }

    @Override
    public Set<UUID> possibleTargets(UUID sourceId, UUID playerId, Game game) {
        Set<UUID> possibleTargets = new HashSet<>();
        Player player = game.getPlayer(playerId);
        if (player != null) {
            for (Card card : player.getHand().getCards(filter, sourceId, playerId, game)) {
                if (sourceId == null || isNotTarget() || !game.replaceEvent(GameEvent.getEvent(GameEvent.EventType.TARGET, card.getId(), sourceId, playerId))) {
                    possibleTargets.add(card.getId());
                }
            }
        }
        return possibleTargets;
    }

    @Override
    public boolean canChoose(UUID sourceId, UUID sourceControllerId, Game game) {
        int possibleTargets = 0;
        Player player = game.getPlayer(sourceControllerId);
        if (player != null) {
            for (Card card : player.getHand().getCards(filter, sourceId, sourceControllerId, game)) {
                if (sourceId == null || isNotTarget() || !game.replaceEvent(GameEvent.getEvent(GameEvent.EventType.TARGET, card.getId(), sourceId, sourceControllerId))) {
                    possibleTargets++;
                    if (possibleTargets >= this.minNumberOfTargets) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public TargetCardInHand copy() {
        return new TargetCardInHand(this);
    }

    @Override
    public String getTargetedName(Game game) {
        return filter.getMessage();
    }
}
