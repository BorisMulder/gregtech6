package gregtech.tileentity.energy.reactors;

import gregapi.data.LH;
import net.minecraft.item.ItemStack;

import java.util.List;

public class MultiTileEntityReactorRodEmpty extends MultiTileEntityReactorRodBase {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
		aList.add(LH.Chat.CYAN + "Empty Reactor Rod, transparent to Neutrons.");
	}

	@Override
	public int emitToAndReflectFrom(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated) {
		//TODO get adjacent rod in the right direction and call this method again
		return 0;
	}

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.empty";}
}
