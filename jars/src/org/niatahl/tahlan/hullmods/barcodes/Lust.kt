package org.niatahl.tahlan.hullmods.barcodes

import com.fs.starfarer.api.Global
import com.fs.starfarer.api.combat.BaseHullMod
import com.fs.starfarer.api.combat.ShipAPI
import com.fs.starfarer.api.fleet.FleetMemberAPI
import org.niatahl.tahlan.utils.Utils

class Lust : BaseHullMod() {
    override fun applyEffectsAfterShipCreation(ship: ShipAPI, id: String) {
        val cnt = ship.variant.sMods.count()
        ship.mutableStats.apply {
            maxSpeed.modifyMult(id, 1f + cnt * AMOUNT)
            acceleration.modifyMult(id, 1f + cnt * AMOUNT)
            deceleration.modifyMult(id, 1f + cnt * AMOUNT)
            turnAcceleration.modifyMult(id, 1f + cnt * AMOUNT)
            maxTurnRate.modifyMult(id, 1f + cnt * AMOUNT)
        }
    }

    override fun advanceInCampaign(member: FleetMemberAPI, amount: Float) {
        if (member.fleetCommander == null || member.fleetData?.fleet == null) return
        if (member.fleetCommander == Global.getSector().playerPerson || member.fleetData.fleet.isPlayerFleet) {
            member.variant.removePermaMod("tahlan_lust")
            member.variant.removeMod("tahlan_lust")
        }
    }

    override fun getDescriptionParam(index: Int, hullSize: ShipAPI.HullSize?): String? {
        return when (index) {
            0 -> "${AMOUNT * 100}${Utils.txt("%")}"
            else -> null
        }
    }

    companion object {
        const val AMOUNT = 0.02f
    }
}