package lotto.game;

import lotto.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.ticket.TicketValidation.ERROR_MESSAGE_INVALID_INPUT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoCountTest {

    @Test
    @DisplayName("로또 개수 객체 생성")
    void lottoCountCreate() {
        LottoCount lottoCount = new LottoCount(new Money("10000"));
        assertThat(lottoCount).isEqualTo(new LottoCount(new Money("10000")));
    }

    @Test
    @DisplayName("구매 금액에 따른 로또 티켓 장수확인")
    void lottoCount() {
        LottoCount lottoCount = new LottoCount(new Money("14000"));
        assertThat(lottoCount.getLottoCount()).isEqualTo(14);
    }

    @Test
    @DisplayName("수동으로 구매할 로또 수 입력에 따른 LottoCount 생성")
    void manualLottoCountCreate() {
        LottoCount lottoCount = new LottoCount("3");
        assertThat(lottoCount).isEqualTo(new LottoCount("3"));
    }

    @Test
    @DisplayName("검증: 숫자로 입력되어야 한다.")
    void checkNumber() {
        assertThatThrownBy(() ->
                new LottoCount("^")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_INPUT);
    }
}
