package sample.ui.config;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.scheduling.annotation.Scheduled;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

@Configuration
public class MonitoringConfig {

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter(@Named("endpointMBeanExporter") MBeanExporter exporter) {
        return new JmxMetricWriter(exporter);
    }

    @Bean
    GraphiteReporter graphiteExporter(MetricRegistry metricRegistry) {
        Graphite graphite = new Graphite("localhost", 2003);
        GraphiteReporter reporter = GraphiteReporter.forRegistry(metricRegistry).prefixedWith("boot").build(graphite);
        reporter.start(500, TimeUnit.MILLISECONDS);
        return reporter;
    }

    @Bean
    MyCustomMetric myCustomMetric(GaugeService gaugeService) {
        return new MyCustomMetric(gaugeService);
    }

    public static class MyCustomMetric {

        private final GaugeService gaugeService;
        private final Random random;

        public MyCustomMetric(GaugeService gaugeService) {
            this.gaugeService = gaugeService;
            this.random = new Random();
        }

        @Scheduled(fixedRate=5000)
        public void measure() {
            gaugeService.submit("gauge.custom", random.nextInt(100));
        }
    }
}
