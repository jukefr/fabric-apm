package fr.juke.fabric_apm.mixin.server;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import fr.juke.fabric_apm.FabricAPM;
import net.minecraft.server.MinecraftServer;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Unique
	private static Transaction fabric_apm_transaction;

	@Inject(at = @At("HEAD"), method = "startServer")
	private static void main(Function<Thread, CallbackI.S> serverFactory, CallbackInfoReturnable<CallbackI.S> cir) {
		fabric_apm_transaction = ElasticApm.startTransaction();
		fabric_apm_transaction.setName("MinecraftServer#startServer");
		fabric_apm_transaction.setType(Transaction.TYPE_REQUEST);
			FabricAPM.logger.info("Start server head");
	}

	@Inject(at = @At("RETURN"), method = "startServer")
	private static void mainReturn(Function<Thread, CallbackI.S> serverFactory, CallbackInfoReturnable<CallbackI.S> cir) {
		fabric_apm_transaction.end();
		FabricAPM.logger.info("Start server return");
	}
}

