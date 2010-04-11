/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 * 
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 * 
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 * 
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 * 
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */

package mage.sets.worldwake;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Zone;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldStaticAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCosts;
import mage.abilities.effects.common.BecomesCreatureSourceEOTEffect;
import mage.abilities.effects.common.EntersBattlefieldTappedEffect;
import mage.abilities.keyword.ReachAbility;
import mage.abilities.mana.GreenManaAbility;
import mage.abilities.mana.WhiteManaAbility;
import mage.cards.CardImpl;
import mage.game.permanent.token.Token;
import mage.sets.Worldwake;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class StirringWildwood extends CardImpl {

	public StirringWildwood(UUID ownerId) {
		super(ownerId, "Stirring Wildwood", new CardType[]{CardType.LAND}, null);
		this.expansionSetId = Worldwake.getInstance().getId();
		this.art = "126541_typ_reg_sty_010.jpg";
		this.addAbility(new EntersBattlefieldStaticAbility(new EntersBattlefieldTappedEffect()));
		this.addAbility(new GreenManaAbility());
		this.addAbility(new WhiteManaAbility());
		this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new BecomesCreatureSourceEOTEffect(new StirringWildwoodToken()), new ManaCosts("{1}{G}{W}")));
	}

}

class StirringWildwoodToken extends Token {

	public StirringWildwoodToken() {
		super("", "3/4 green and white Elemental creature with reach");
		cardType.add(CardType.CREATURE);
		subtype.add("Elemental");
		color.setGreen(true);
		color.setWhite(true);
		power = new MageInt(3);
		toughness = new MageInt(4);
		addAbility(ReachAbility.getInstance());
	}

}
