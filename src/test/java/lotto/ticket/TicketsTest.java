package lotto.ticket;

import lotto.game.LottoCount;
import lotto.money.Money;
import lotto.ranking.Ranking;
import lotto.ticket.strategy.RandomNumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TicketsTest {
    @Test
    @DisplayName("티켓들 생성 확인")
    void ticketsCreate() {
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ticketList.add(new Ticket(new RandomNumbersGenerator().generate()));
        }
        Tickets tickets = new Tickets(ticketList);
        assertThat(tickets.getTickets().size()).isEqualTo(20);
    }

    @Test
    @DisplayName("티켓들 결과 생성 확인")
    void checkTicketsRanking() {
        WinnerTicket winnerTicket = new WinnerTicket("1, 2, 3, 4, 5, 6");
        BonusBall bonusBall = new BonusBall("7", winnerTicket);
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            ticketList.add(new Ticket(new RandomNumbersGenerator().generate()));
        }
        Tickets tickets = new Tickets(ticketList);
        List<Ranking> result = tickets.makeResult(winnerTicket, bonusBall);
        assertThat(result.size()).isEqualTo(2000);
    }

    @Test
    @DisplayName("수동 구매 후 남은 카운트로 자동 구매")
    void manualTicketAndAutoTicketPurchase() {
        Money purchaseMoney = new Money("14000");
        LottoCount lottoCount = new LottoCount(purchaseMoney);
        LottoCount manualLottoCount = new LottoCount("3");
        LottoCount remainCount = lottoCount.consumeTicket(manualLottoCount);
        List<Ticket> ticketList = new ArrayList<>();
        while (remainCount.isGreaterThanZero()) {
            remainCount = remainCount.decreaseOne();
            ticketList.add(new Ticket(new RandomNumbersGenerator().generate()));
        }
        Tickets tickets = new Tickets(ticketList);
        assertThat(tickets.getTickets().size()).isEqualTo(11);
    }
}
