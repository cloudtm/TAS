import eu.cloudtm.autonomicManager.commons.EvaluatedParam;
import eu.cloudtm.autonomicManager.commons.ForecastParam;
import eu.cloudtm.autonomicManager.commons.Param;
import eu.cloudtm.autonomicManager.oracles.InputOracle;
import parse.radargun.Ispn5_2CsvParser;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 27/08/13
 */
public class CsvInputOracle implements InputOracle {

   Ispn5_2CsvParser csvParser;

   @Override
   public Object getParam(Param param) {
      switch (param) {
         case NumNodes:
            return numNodes();
         case ReplicationDegree:
            return replicationDegree();
         case AvgPutsPerWrTransaction:
            return putsPerWrXact();
         case AvgPrepareCommandSize:
            return prepareCommandSize();
         case MemoryInfo_used:
            return memory();
         case AvgGetsPerROTransaction:
            return getsPerRoXact();
         case AvgGetsPerWrTransaction:
            return getsPerWrXact();
         case LocalUpdateTxLocalServiceTime:
            return localUpdateTxLocalServiceTime();
         case LocalUpdateTxPrepareServiceTime:
            return localUpdateTxPrepareServiceTime();
         case LocalUpdateTxCommitServiceTime:
            return localUpdateTxCommitServiceTime();
         case LocalUpdateTxLocalRollbackServiceTime:
            return localUpdateTxLocalRollbackServiceTime();
         case LocalUpdateTxRemoteRollbackServiceTime:
            return localUpdateTxRemoteRollbackServiceTime();
         case RemoteGetServiceTime:
            return remoteGetServiceTime();
         case GMUClusteredGetCommandServiceTime:
            return gmuClusterGetCommandServiceTime();
         case RemoteUpdateTxPrepareServiceTime:
            return remoteUpdateTxPrepareServiceTime();
         case RemoteUpdateTxCommitServiceTime:
            return remoteUpdateTxCommitServiceTime();
         case RemoteUpdateTxRollbackServiceTime:
            return remoteUpateTxRollbackServiceTime();
         case ReadOnlyTxTotalCpuTime:
            return localReadOnlyTxTotalCpuTime();
         default:
            throw new IllegalArgumentException("Param " + param + " is not present");
      }

   }

   @Override
   public Object getEvaluatedParam(EvaluatedParam evaluatedParam) {
      switch (evaluatedParam) {
         case MAX_ACTIVE_THREADS:
            return numThreadsPerNode();
         case ACF:
            return acf();
         case CORE_PER_CPU:
            return cpus();
         default:
            throw new IllegalArgumentException("Param " + evaluatedParam + " is not present");
      }
   }

   @Override
   public Object getForecastParam(ForecastParam forecastParam) {
      switch (forecastParam) {
         case ReplicationProtocol:
            return replicationProtocol();
         default:
            throw new IllegalArgumentException("Param " + forecastParam + " is not present");
      }
   }

   /**
    * AD HOC METHODS *
    */

   private double numNodes() {
      return csvParser.getNumNodes();
   }

   private double replicationDegree() {
      return csvParser.replicationDegree();
   }

   private double putsPerWrXact() {
      return csvParser.putsPerWrXact();
   }

   private double numThreadsPerNode() {
      return csvParser.numThreads();
   }

   private double prepareCommandSize() {
      return csvParser.sizePrepareMsg();
   }

   private double acf() {
      return 1.0D / csvParser.numKeys();
   }

   private double memory() {
      return 1e-6 * csvParser.mem();
   }

   private double cpus() {
      return 2;
   }

   private String replicationProtocol() {
      return csvParser.getReplicationProtocol();
   }

   private double getsPerRoXact() {
      return csvParser.readsPerROXact();
   }

   private double getsPerWrXact() {
      return csvParser.readsPerWrXact();
   }

   private double localUpdateTxLocalServiceTime() {
      return csvParser.localServiceTimeWrXact();
   }

   private double localUpdateTxPrepareServiceTime() {
      return csvParser.getAvgParam("localUpdateTxPrepareServiceTime");
   }

   private double localUpdateTxCommitServiceTime() {
      return csvParser.getAvgParam("localUpdateTxCommitServiceTime");
   }

   private double localUpdateTxLocalRollbackServiceTime() {
      return csvParser.getAvgParam("localUpdateTxLocalRollbackServiceTime");
   }

   private double localUpdateTxRemoteRollbackServiceTime() {
      return csvParser.getAvgParam("localUpdateTxRemoteRollbackServiceTime");
   }

   private double localReadOnlyTxTotalCpuTime() {
      return csvParser.localServiceTimeROXact();
   }

   private double remoteGetServiceTime() {
      return csvParser.localRemoteGetServiceTime();
   }

   private double remoteUpdateTxPrepareServiceTime() {
      return csvParser.remotePrepareServiceTime();
   }

   private double remoteUpdateTxCommitServiceTime() {
      return csvParser.remoteCommitCommandServiceTime();
   }

   private double remoteUpateTxRollbackServiceTime() {
      return csvParser.remoteRollbackServiceTime();
   }

   private double gmuClusterGetCommandServiceTime() {
      return csvParser.remoteRemoteGetServiceTime();
   }


}
