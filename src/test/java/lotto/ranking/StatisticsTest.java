package lotto.ranking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatisticsTest {
    @Test
    @DisplayName("당첨 통계 생성")
    void statisticsCreate() {
        Statistics statistics = new Statistics(Arrays.asList(Ranking.FIRST, Ranking.FORTH, Ranking.FORTH, Ranking.FORTH));
        assertThat(statistics.findRankingCount(Ranking.FIRST)).isEqualTo(1);
        assertThat(statistics.findRankingCount(Ranking.SECOND)).isEqualTo(0);
        assertThat(statistics.findRankingCount(Ranking.THIRD)).isEqualTo(0);
        assertThat(statistics.findRankingCount(Ranking.FORTH)).isEqualTo(3);
    }

    @Test
    @DisplayName("당첨 결과에 따른 당첨금 총액 확인")
    void totalPrize() {
        Statistics statistics = new Statistics(Arrays.asList(Ranking.FIRST, Ranking.FORTH, Ranking.FORTH, Ranking.FORTH));
        assertThat(statistics.getTotalPrize()).isEqualTo("2000150000");
    }
}
