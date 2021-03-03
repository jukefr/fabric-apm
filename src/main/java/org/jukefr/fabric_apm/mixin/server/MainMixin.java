package org.jukefr.fabric_apm.mixin.server;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import co.elastic.apm.attach.ElasticApmAttacher;
import net.minecraft.server.Main;
import org.jukefr.fabric_apm.FabricAPM;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class MainMixin {
	@Unique
	private static Transaction fabric_apm_transaction;

	@Inject(at = @At("HEAD"), method = "main")
	private static void main(CallbackInfo info) {
		ElasticApmAttacher.attach("elasticapm.properties");
		FabricAPM.logger.info("Attached Elastic APM agent");

		fabric_apm_transaction = ElasticApm.startTransaction();
		fabric_apm_transaction.setName("Main#main");
		fabric_apm_transaction.setType(Transaction.TYPE_REQUEST);
		FabricAPM.logger.info("Main head");

	}

	@Inject(at = @At("RETURN"), method = "main")
	private static void mainReturn(CallbackInfo info) {
		fabric_apm_transaction.end();
		FabricAPM.logger.info("Main return");
	}
}

