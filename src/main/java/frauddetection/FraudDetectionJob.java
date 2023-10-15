/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package frauddetection;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.walkthrough.common.sink.AlertSink;
import org.apache.flink.walkthrough.common.entity.Alert;

import java.util.Arrays;
import java.util.List;

/**
 * Skeleton code for the datastream walkthrough
 */
public class FraudDetectionJob {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		List<MyTransaction> transactionList = Arrays.asList(
				new MyTransaction(1L, System.currentTimeMillis(), 13.0),
				new MyTransaction(1L, System.currentTimeMillis(), 25.0),
				new MyTransaction(1L, System.currentTimeMillis(), 0.09),
				new MyTransaction(1L, System.currentTimeMillis(), 510.0),
				new MyTransaction(1L, System.currentTimeMillis(), 102.0),
				new MyTransaction(1L, System.currentTimeMillis(), 91.0),
				new MyTransaction(1L, System.currentTimeMillis(), 0.1),
				new MyTransaction(1L, System.currentTimeMillis(), 0.2),
				new MyTransaction(1L, System.currentTimeMillis(), 600.0));



		DataStream<MyTransaction> transactions = env
			.addSource(new MyTransactionSource(transactionList))
			.name("transactions");

		DataStream<Alert> alerts = transactions
			.keyBy(MyTransaction::getAccountId)
			.process(new FraudDetector())
			.name("fraud-detector");

		alerts
			.addSink(new AlertSink())
			.name("send-alerts");

		env.execute("Fraud Detection");
	}
}
