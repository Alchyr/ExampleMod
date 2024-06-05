package examplemod.cards.examples;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.cards.BaseCard;
import examplemod.util.CardStats;

public class InfiniteUpgradeAttack extends BaseCard {
    public static final String ID = makeID("InfiniteUpgradeAttack");
    private final static CardStats CARD_STATS = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1);

    private static final int DAMAGE = 4;

    public InfiniteUpgradeAttack() {
        super(ID, CARD_STATS, false);

        setDamage(DAMAGE);
        setMagic(3);
    }

    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(5 * this.timesUpgraded);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public AbstractCard makeCopy() {
        return new InfiniteUpgradeAttack();
    }
}