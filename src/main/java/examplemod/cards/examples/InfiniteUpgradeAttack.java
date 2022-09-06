package examplemod.cards.examples;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.cards.BaseCard;
import examplemod.util.CardInfo;

import static examplemod.BasicMod.makeID;

public class InfiniteUpgradeAttack extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "InfiniteUpgradeAttack",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);
    // skill

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 8;

    public InfiniteUpgradeAttack() {
        super(cardInfo, false);

        setDamage(DAMAGE);
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