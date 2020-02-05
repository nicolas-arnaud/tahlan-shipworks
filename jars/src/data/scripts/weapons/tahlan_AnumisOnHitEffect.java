package data.scripts.weapons;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import data.scripts.util.MagicLensFlare;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

public class tahlan_AnumisOnHitEffect implements OnHitEffectPlugin {

    //Variables for explosion visuals
    private static final Color EXPLOSION_COLOR = new Color(100f / 255f, 205f / 255f, 255f / 255f, 130f / 255f);
    private static final float EXPLOSION_SIZE = 180f;
    private static final float EXPLOSION_DURATION = 0.7f;

    private static final float BONUS_DAMAGE = 100f;

    @Override
    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, CombatEngineAPI engine) {

        if (!(target instanceof ShipAPI)){
            return;
        }

        if (projectile.didDamage()) {
            Global.getCombatEngine().applyDamage(target, point, BONUS_DAMAGE, DamageType.ENERGY, BONUS_DAMAGE, false, false, null, true);
        }
        Global.getCombatEngine().spawnExplosion(point, new Vector2f(0f, 0f), EXPLOSION_COLOR, EXPLOSION_SIZE, EXPLOSION_DURATION);
        MagicLensFlare.createSharpFlare(engine, projectile.getSource(), projectile.getLocation(), 8, 500, 0, new Color(186, 240, 255), new Color(255, 255, 255));

    }
}
