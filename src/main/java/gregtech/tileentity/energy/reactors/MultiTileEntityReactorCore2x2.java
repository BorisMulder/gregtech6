/**
 * Copyright (c) 2025 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.tileentity.energy.reactors;

import gregapi.old.Textures;
import gregapi.render.*;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import net.minecraft.block.Block;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorCore2x2 extends MultiTileEntityReactorCore implements ITileEntitySwitchableMode {
	@Override
	public int numSlots() { return 4; }

	@Override
	public int slotActivated(float aHitX, float aHitY, float aHitZ) {
		int slot = 0;
		if (aHitZ >= 0.5) slot += 1;
		if (aHitX >= 0.5) slot += 2;
		return slot;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void updateAdjacentCores() {
		DelegatorTileEntity<MultiTileEntityReactorCore>[] tAdjacents = new DelegatorTileEntity[4];
		DelegatorTileEntity<MultiTileEntityReactorCore> tAdjacent;
		DelegatorTileEntity
		tAdjacentTE = getAdjacentTileEntity(SIDE_Z_NEG); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[0] = tAdjacentTE;
		tAdjacentTE = getAdjacentTileEntity(SIDE_Z_POS); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[1] = tAdjacentTE;
		tAdjacentTE = getAdjacentTileEntity(SIDE_X_NEG); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[2] = tAdjacentTE;
		tAdjacentTE = getAdjacentTileEntity(SIDE_X_POS); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[3] = tAdjacentTE;

		// Get amount of neutrons emitted from each adjacent rod, and send them to its neighbours, one by one.
		int tNeutronCount = getReactorRodNeutronEmission(0);
		boolean tModerated = isReactorRodModerated(0);
		if (tNeutronCount != 0 || tModerated) {
			neutronCountsIn[0] += emitNeutronsTo(1, tNeutronCount, tModerated);
			neutronCountsIn[0] += emitNeutronsTo(2, tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_Z_NEG-2]; if (tAdjacent != null) neutronCountsIn[0] += tAdjacent.mTileEntity.emitNeutronsTo(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_X_NEG-2]; if (tAdjacent != null) neutronCountsIn[0] += tAdjacent.mTileEntity.emitNeutronsTo(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
		}

		tNeutronCount = getReactorRodNeutronEmission(1);
		tModerated = isReactorRodModerated(1);
		if (tNeutronCount != 0 || tModerated) {
			neutronCountsIn[1] += emitNeutronsTo(0, tNeutronCount, tModerated);
			neutronCountsIn[1] += emitNeutronsTo(3, tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_Z_POS-2]; if (tAdjacent != null) neutronCountsIn[1] += tAdjacent.mTileEntity.emitNeutronsTo(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_X_NEG-2]; if (tAdjacent != null) neutronCountsIn[1] += tAdjacent.mTileEntity.emitNeutronsTo(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
		}

		tNeutronCount = getReactorRodNeutronEmission(2);
		tModerated = isReactorRodModerated(2);
		if (tNeutronCount != 0 || tModerated) {
			neutronCountsIn[2] += emitNeutronsTo(0, tNeutronCount, tModerated);
			neutronCountsIn[2] += emitNeutronsTo(3, tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_Z_NEG-2]; if (tAdjacent != null) neutronCountsIn[2] += tAdjacent.mTileEntity.emitNeutronsTo(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_X_POS-2]; if (tAdjacent != null) neutronCountsIn[2] += tAdjacent.mTileEntity.emitNeutronsTo(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
		}

		tNeutronCount = getReactorRodNeutronEmission(3);
		tModerated = isReactorRodModerated(3);
		if (tNeutronCount != 0 || tModerated) {
			neutronCountsIn[3] += emitNeutronsTo(1, tNeutronCount, tModerated);
			neutronCountsIn[3] += emitNeutronsTo(2, tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_Z_POS-2]; if (tAdjacent != null) neutronCountsIn[3] += tAdjacent.mTileEntity.emitNeutronsTo(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
			tAdjacent = tAdjacents[SIDE_X_POS-2]; if (tAdjacent != null) neutronCountsIn[3] += tAdjacent.mTileEntity.emitNeutronsTo(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
		}
	}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case  7: return box(aBlock, PX_P[ 2], PX_P[ 1], PX_P[ 2], PX_N[10], PX_P[17], PX_N[10]);
		case  8: return box(aBlock, PX_P[ 2], PX_P[ 1], PX_P[10], PX_N[10], PX_P[17], PX_N[ 2]);
		case  9: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[ 2], PX_N[ 2], PX_P[17], PX_N[10]);
		case 10: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[10], PX_N[ 2], PX_P[17], PX_N[ 2]);
		default: return super.setBlockBounds2(aBlock, aRenderPass, aShouldSideBeRendered);
		}
	}

	private static final IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/face2")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/face2")
	};

	@Override protected IIconContainer[] getColoredIcons() { return sColoreds; }
	@Override protected IIconContainer[] getOverlayIcons() { return sOverlays; }

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core";} // Yeah Naming Convention doesn't work on the first one I added, just imagine a ".2x2" at the end of this String.
}
