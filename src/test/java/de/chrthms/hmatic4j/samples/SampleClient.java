/*
 * Copyright 2016 Christian Thomas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.chrthms.hmatic4j.samples;

import de.chrthms.hmatic4j.HMaticAPI;
import de.chrthms.hmatic4j.core.commands.enums.RxMode;
import de.chrthms.hmatic4j.core.commands.impl.get.value.GetValueLevel;
import de.chrthms.hmatic4j.core.commands.impl.init.LogicLayerInit;
import de.chrthms.hmatic4j.core.commands.impl.set.value.SetValueLevel;
import de.chrthms.hmatic4j.core.commands.impl.set.value.SetValueStop;
import java.util.Optional;

/**
 *
 * @author christian
 */
public class SampleClient {
    
    public static void main(String... args) {
                
        Optional<String> regId = HMaticAPI.getInstance()
                .rpcServerAddress("127.0.0.1")
                .observe()
                .onceOnly(true)
                .start((incomingEvent) -> {
                    // do something with the input!
                });
        
        if (true) return;
        
        Object result = HMaticAPI.getInstance()
                .rpcServerAddress("127.0.0.1")
                .service()
                .connection()
                .wireless()
                .command(null /* place command here */)
                .singleResult();
        
        HMaticAPI.getInstance()
                .config()
                .configPath("some/path/to/properties/file")
                .service()
                .connection()
                .wired()                
                .command(new SetValueLevel()
                    .value(0.8)
                    .deviceAddress("abdcdfgdf4324")
                    .deviceChannel("1"))
                .execute();
        
        HMaticAPI.getInstance()
                .rpcServerAddress("127.0.0.1")
                .wireless()
                .command(new SetValueStop()
                    .deviceAddress("32scdef1234567")
                    .deviceChannel("1"))
                .execute();
        
        HMaticAPI.getInstance()
                .config()
                .wireless()
                .rxMode(RxMode.BURST)
                .command(new SetValueStop()
                    .deviceAddress("abcdef1234567")
                    .deviceChannel("1"))
                .execute();
        
        Double currentLevel = HMaticAPI.getInstance()
                .wireless()
                .command(new GetValueLevel()
                    .deviceAddress("abdcdfgdf4324")
                    .deviceChannel("1"))
                .singleResult(Double.class);
        
        LogicLayerInit logicLayerInit = new LogicLayerInit();
        
    }
    
}
