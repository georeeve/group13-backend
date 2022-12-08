package com.example.group13backend.utils;

import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import de.mkammerer.snowflakeid.options.Options;
import de.mkammerer.snowflakeid.structure.Structure;
import de.mkammerer.snowflakeid.time.MonotonicTimeSource;
import de.mkammerer.snowflakeid.time.TimeSource;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SnowflakeUtil {

    private final TimeSource timeSource = new MonotonicTimeSource(Instant.parse("2022-01-01T00:00:00Z"));
    private final Structure structure = new Structure(41, 10, 12);
    private final Options options = new Options(Options.SequenceOverflowStrategy.THROW_EXCEPTION);
    private final SnowflakeIdGenerator snowflakeIdGenerator = SnowflakeIdGenerator
            .createCustom(0, timeSource, structure, options);

    public String newId() {
        return Long.toString(snowflakeIdGenerator.next());
    }
}
