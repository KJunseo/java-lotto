package lotto.game;

import lotto.money.Money;
import lotto.ranking.Statistics;
import lotto.ticket.BonusBall;
import lotto.ticket.Ticket;
import lotto.ticket.Tickets;
import lotto.ticket.WinnerTicket;
import lotto.ticket.strategy.ManualNumbersGenerator;
import lotto.ticket.strategy.RandomNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        LottoCount lottoCount = possibleLottoCount(money);
        LottoCount manualTicketAmount = manualTicketAmount();
        LottoCount autoTicketAmount = lottoCount.consumeTicket(manualTicketAmount);
        Tickets totalTicket = ticketPurchase(manualTicketAmount, autoTicketAmount);
        verifyResult(money, totalTicket);
    }

    private Money generateMoney() {
        try {
            OutputView.enterPurchaseMoney();
            return InputView.inputMoney();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateMoney();
        }
    }

    private LottoCount possibleLottoCount(Money money) {
        try {
            return new LottoCount(money);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return possibleLottoCount(money);
        }
    }

    private LottoCount manualTicketAmount() {
        try {
            OutputView.enterManualTicketAmount();
            return InputView.inputManualTicketAmount();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return manualTicketAmount();
        }
    }

    private Tickets ticketPurchase(LottoCount manualTicketAmount, LottoCount autoTicketAmount) {
        Tickets manualTickets = manualTicketGenerate(manualTicketAmount);
        Tickets autoTickets = autoTicketGenerate(autoTicketAmount);
        OutputView.noticeLottoCount(manualTicketAmount, autoTicketAmount);
        Tickets totalTicket = Tickets.joinTicket(manualTickets, autoTickets);
        OutputView.showTickets(totalTicket);
        return totalTicket;
    }

    private Tickets manualTicketGenerate(LottoCount count) {
        try {
            OutputView.enterManualTicketNumber();
            List<Ticket> tickets = new ArrayList<>();
            ticketGenerate(count, tickets);
            return new Tickets(tickets);
        } catch (RuntimeException e) {
            OutputView.printError(e);
            return manualTicketGenerate(count);
        }
    }

    private void ticketGenerate(LottoCount count, List<Ticket> tickets) {
        while (count.isGreaterThanZero()) {
            count = count.decreaseOne();
            tickets.add(new Ticket(new ManualNumbersGenerator(InputView.inputNumbers()).generate()));
        }
    }

    private Tickets autoTicketGenerate(LottoCount count) {
        List<Ticket> tickets = new ArrayList<>();
        while (count.isGreaterThanZero()) {
            count = count.decreaseOne();
            tickets.add(new Ticket(new RandomNumbersGenerator().generate()));
        }
        return new Tickets(tickets);
    }

    private void verifyResult(Money money, Tickets totalTicket) {
        WinnerTicket winnerTicket = verifyWinnerTicket();
        BonusBall bonusBall = verifyBonusBall(winnerTicket);
        Statistics statistics = generateStatistics(totalTicket, winnerTicket, bonusBall);
        OutputView.showProfit(statistics.calculateProfit(money));
    }

    private WinnerTicket verifyWinnerTicket() {
        try {
            OutputView.enterWinnerTicket();
            return InputView.inputWinnerTicket();
        } catch (RuntimeException e) {
            OutputView.printError(e);
            return verifyWinnerTicket();
        }
    }

    private BonusBall verifyBonusBall(WinnerTicket winnerTicket) {
        try {
            OutputView.enterBonusBall();
            return InputView.inputBonusBall(winnerTicket);
        } catch (RuntimeException e) {
            OutputView.printError(e);
            return verifyBonusBall(winnerTicket);
        }
    }

    private Statistics generateStatistics(Tickets tickets, WinnerTicket winnerTicket, BonusBall bonusBall) {
        Statistics statistics = new Statistics(tickets.makeResult(winnerTicket, bonusBall));
        OutputView.noticeStatistics(statistics);
        return statistics;
    }
}