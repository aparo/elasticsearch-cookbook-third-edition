package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class CustomEnglishAnalyzerProvider extends AbstractIndexAnalyzerProvider<EnglishAnalyzer> {
    public static String NAME = "custom_english";

    private final EnglishAnalyzer analyzer;

    public CustomEnglishAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings, boolean useSmart) {
        super(indexSettings, name, settings);

        analyzer = new EnglishAnalyzer(
                Analysis.parseStopWords(env, settings, EnglishAnalyzer.getDefaultStopSet(), true),
                Analysis.parseStemExclusion(settings, CharArraySet.EMPTY_SET));
    }

    public static CustomEnglishAnalyzerProvider getCustomEnglishAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        return new CustomEnglishAnalyzerProvider(indexSettings, env, name, settings, true);
    }

    @Override
    public EnglishAnalyzer get() {
        return this.analyzer;
    }
}