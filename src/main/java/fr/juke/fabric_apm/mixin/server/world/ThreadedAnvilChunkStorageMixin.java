package fr.juke.fabric_apm.mixin.server.world;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import com.mojang.datafixers.util.Either;
import fr.juke.fabric_apm.FabricAPM;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorageMixin {
	@Unique
	private static Transaction fabric_apm_transaction;

	@Inject(at = @At("HEAD"), method = "loadChunk")
	private void main(ChunkPos pos, CallbackInfoReturnable<CompletableFuture<Either<Chunk, ChunkHolder.Unloaded>>> cir) {
		fabric_apm_transaction = ElasticApm.startTransaction();
		fabric_apm_transaction.setName("ThreadedAnvilChunkStorage#loadChunk");
		fabric_apm_transaction.setType(Transaction.TYPE_REQUEST);
		FabricAPM.logger.info("loadChunk head");

	}

	@Inject(at = @At("RETURN"), method = "loadChunk")
	private void mainReturn(ChunkPos pos, CallbackInfoReturnable<CompletableFuture<Either<Chunk, ChunkHolder.Unloaded>>> cir) {
		fabric_apm_transaction.end();
		FabricAPM.logger.info("loadChunk return");
	}
}

