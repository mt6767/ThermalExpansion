package thermalexpansion.plugins.mfr;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class DrinkHandlerPyrotheum implements ILiquidDrinkHandler {

	public static DrinkHandlerPyrotheum instance = new DrinkHandlerPyrotheum();

	@Override
	public void onDrink(EntityPlayer player) {

		player.attackEntityFrom(new InternalPyrotheumDamage(), 15);
		player.setFire(30);
		NBTTagCompound tag = player.getEntityData();
		tag.setLong("drankLavaTime", player.worldObj.getTotalWorldTime());
	}

	protected class InternalPyrotheumDamage extends DamageSource {

		public InternalPyrotheumDamage() {

			super(DamageSource.lava.damageType);
			this.setDamageBypassesArmor();
			this.setFireDamage();
			this.setDifficultyScaled();
		}
	}

}
