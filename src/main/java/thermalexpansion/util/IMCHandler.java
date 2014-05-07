package thermalexpansion.util;

import java.util.Locale;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import thermalexpansion.ThermalExpansion;
import thermalexpansion.fluid.TEFluids;
import thermalexpansion.util.crafting.CrucibleManager;
import thermalexpansion.util.crafting.FurnaceManager;
import thermalexpansion.util.crafting.PulverizerManager;
import thermalexpansion.util.crafting.SawmillManager;
import thermalexpansion.util.crafting.SmelterManager;
import thermalexpansion.util.crafting.TransposerManager;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class IMCHandler {

	public static IMCHandler instance = new IMCHandler();

	public void handleIMC(IMCEvent theIMC) {

		NBTTagCompound theNBT;
		for (IMCMessage theMessage : theIMC.getMessages()) {
			try {
				if (theMessage.isNBTMessage()) {
					theNBT = theMessage.getNBTValue();

					if (theMessage.key.equalsIgnoreCase("CrucibleRecipe")) {
						CrucibleManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")), FluidStack
								.loadFluidStackFromNBT(theNBT.getCompoundTag("output")), theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("FurnaceRecipe")) {
						FurnaceManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")), ItemStack
								.loadItemStackFromNBT(theNBT.getCompoundTag("output")), theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("PulverizerRecipe")) {
						if (theNBT.hasKey("secondaryChance")) {
							PulverizerManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryOutput")), theNBT.getInteger("secondaryChance"),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						} else if (theNBT.hasKey("secondaryOutput")) {
							PulverizerManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryOutput")),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						} else {
							PulverizerManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						}
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("SmelterRecipe")) {
						if (theNBT.hasKey("secondaryChance")) {
							SmelterManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryInput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryInput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryOutput")), theNBT.getInteger("secondaryChance"),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						} else if (theNBT.hasKey("secondaryOutput")) {
							SmelterManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryInput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryInput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryOutput")),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						} else {
							SmelterManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryInput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryInput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						}
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("SmelterBlastOreType")) {
						if (theNBT.hasKey("oreType")) {
							SmelterManager.addBlastOreName(theNBT.getString("oreType"));
						}
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("SawmillRecipe")) {
						if (theNBT.hasKey("secondaryChance")) {
							SawmillManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryOutput")), theNBT.getInteger("secondaryChance"),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						} else if (theNBT.hasKey("secondaryOutput")) {
							SawmillManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("secondaryOutput")),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						} else {
							SawmillManager.addRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")),
									ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("primaryOutput")),
									theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						}
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("TransposerFillRecipe")) {
						TransposerManager.addFillRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")), ItemStack
								.loadItemStackFromNBT(theNBT.getCompoundTag("output")), FluidStack.loadFluidStackFromNBT(theNBT.getCompoundTag("fluid")),
								theNBT.getBoolean("reversible"), theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("TransposerExtractRecipe")) {
						TransposerManager.addExtractionRecipe(theNBT.getInteger("energy"), ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("input")),
								ItemStack.loadItemStackFromNBT(theNBT.getCompoundTag("output")),
								FluidStack.loadFluidStackFromNBT(theNBT.getCompoundTag("fluid")), theNBT.getInteger("chance"), theNBT.getBoolean("reversible"),
								theNBT.hasKey("overwrite") ? theNBT.getBoolean("overwrite") : false);
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("MagmaticFuel")) {
						String fluidName = theNBT.getString("fluidName").toLowerCase(Locale.ENGLISH);
						int energy = theNBT.getInteger("energy");

						if (TEFluids.registerMagmaticFuel(fluidName, energy)) {
							TEFluids.configFuels.get("fuels.magmatic", fluidName, energy);
						}
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("CompressionFuel")) {
						String fluidName = theNBT.getString("fluidName").toLowerCase(Locale.ENGLISH);
						int energy = theNBT.getInteger("energy");

						if (TEFluids.registerCompressionFuel(fluidName, energy)) {
							TEFluids.configFuels.get("fuels.compression", fluidName, energy);
						}
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("ReactantFuel")) {
						String fluidName = theNBT.getString("fluidName").toLowerCase(Locale.ENGLISH);
						int energy = theNBT.getInteger("energy");

						if (TEFluids.registerCompressionFuel(fluidName, energy)) {
							TEFluids.configFuels.get("fuels.reactant", fluidName, energy);
						}
						continue;
					}

					else if (theMessage.key.equalsIgnoreCase("Coolant")) {
						String fluidName = theNBT.getString("fluidName").toLowerCase(Locale.ENGLISH);
						int energy = theNBT.getInteger("energy");

						if (TEFluids.registerCoolant(fluidName, energy)) {
							TEFluids.configFuels.get("coolants", fluidName, energy);
						}
						continue;
					}
					ThermalExpansion.log.warning("ThermalExpansion received an invalid IMC from " + theMessage.getSender() + "! Key was " + theMessage.key);
				}
			} catch (Exception e) {
				ThermalExpansion.log.warning("ThermalExpansion received an broken IMC from " + theMessage.getSender() + "!");
				e.printStackTrace();
			}
		}
	}

}
