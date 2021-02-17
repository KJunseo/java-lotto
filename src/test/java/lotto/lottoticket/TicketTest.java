package lotto.lottoticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketTest {
    @Test
    @DisplayName("로또 티켓 생성")
    void ticketCreate() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1,2,3,4,5,6);
        Ticket ticket = new Ticket(numbersGenerator);
        assertThat(ticket).isEqualTo(new Ticket(numbersGenerator));
    }

    @Test
    @DisplayName("1부터 45사이 숫자인지 확인")
    void checkNumberInRange() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 46, 2, 3, 4, 5);
        assertThatThrownBy(()->
                new Ticket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자는 1부터 45사이여야 합니다.");
    }

    @Test
    @DisplayName("로또 숫자 중복 확인")
    void checkDuplicatedNumber() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 1, 2, 3, 4, 5);
        assertThatThrownBy(()->
                new Ticket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복되는 숫자가 존재합니다.");
    }

    @Test
    @DisplayName("로또 숫자 개수 확인")
    void checkSizeOfNumbers() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 2, 3, 4, 5);
        assertThatThrownBy(()->
                new Ticket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자는 여섯개여야 합니다.");
    }

    @Test
    @DisplayName("보너스볼 포함 확인")
    void checkContainBonusBall() {
        BonusBall bonusBall = new BonusBall("6", new WinnerTicket(("1, 2, 3, 4, 5, 8")));
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        Ticket ticket = new Ticket(numbersGenerator);
        assertTrue(ticket.hasContainBonus(bonusBall));
    }
}
