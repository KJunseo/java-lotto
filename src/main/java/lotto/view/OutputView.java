package lotto.view;

import lotto.game.LottoCount;
import lotto.ranking.Ranking;
import lotto.ranking.Statistics;
import lotto.ticket.Ticket;
import lotto.ticket.Tickets;

import java.util.Map;

public class OutputView {
    private static final String ENTER_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ENTER_MANUAL_TICKET_AMOUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String ENTER_MANUAL_TICKET_NUMBER = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String COMPLETE_PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String ENTER_WINNER_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ENTER_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String STATISTICS_TITLE = "당첨 통계";
    private static final String STATISTICS_DIVIDER = "---------";
    private static final String RANKING_RESULT_FORMAT = "%d개 일치 (%d원)- %d개%n";
    private static final String RANKING_SECOND_RESULT_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원)- %d개%n";
    private static final String TOTAL_PROFIT_FORMAT = "총 수익률은 %.2f 입니다.";

    private OutputView() {
    }

    public static void enterPurchaseMoney() {
        System.out.println(ENTER_PURCHASE_MONEY_MESSAGE);
    }

    public static void noticeLottoCount(LottoCount manual, LottoCount auto) {
        System.out.printf(COMPLETE_PURCHASE_MESSAGE, manual.getLottoCount(), auto.getLottoCount());
        System.out.println();
    }

    public static void showTickets(Tickets tickets) {
        for (Ticket ticket : tickets.getTickets()) {
            System.out.println(ticket.getTicket());
        }
    }

    public static void enterWinnerTicket() {
        System.out.println();
        System.out.println(ENTER_WINNER_TICKET_MESSAGE);
    }

    public static void enterBonusBall() {
        System.out.println(ENTER_BONUS_BALL_MESSAGE);
    }

    public static void noticeStatistics(Statistics statistics) {
        System.out.println();
        System.out.println(STATISTICS_TITLE);
        System.out.println(STATISTICS_DIVIDER);
        printRankings(statistics);
    }

    private static void printRankings(Statistics statistics) {
        Map<Ranking, Integer> result = statistics.getStatistics();
        for (Ranking ranking : Ranking.values()) {
            printEachRanking(result, ranking);
        }
    }

    private static void printEachRanking(Map<Ranking, Integer> result, Ranking ranking) {
        if (ranking.equals(Ranking.NOTHING)) {
            return;
        }
        if (ranking.equals(Ranking.SECOND)) {
            System.out.printf(RANKING_SECOND_RESULT_FORMAT, ranking.getMatchCount(), ranking.getPrice(), result.get(ranking));
            return;
        }
        System.out.printf(RANKING_RESULT_FORMAT, ranking.getMatchCount(), ranking.getPrice(), result.get(ranking));
    }

    public static void showProfit(double calculateProfit) {
        System.out.printf(TOTAL_PROFIT_FORMAT, calculateProfit);
        System.out.println();
    }

    public static void printError(RuntimeException e) {
        System.err.println(e.getMessage());
    }

    public static void enterManualTicketAmount() {
        System.out.println(ENTER_MANUAL_TICKET_AMOUNT);
    }

    public static void enterManualTicketNumber() {
        System.out.println(ENTER_MANUAL_TICKET_NUMBER);
    }
}