# Fabric APM

## TODO
- [ ] make apm trace injection smarter/automated (dry the shit up and see if cant use what they (es) do on springboot or whatever and copy over for MC)
- [ ] correlate logs and traces (apm mdc correlation not working rn) (maybe need to pull agent out of mixin and attach it via -javaagent **needsTesting**)
- [ ] add metrics (playercount, tps, loadedchunks etc)
- [ ] be done with the holy trinity of elk inputs (metrics, traces, logs) these are just accessors and send the shit via metricbeat or w/e not much to say about that
- [ ] ?????
- [ ] profit, be able to actually know what is going on your server (**wheeze**)
- [ ] read a java tutorial at some point **maybe**