package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.domain.lottoTicket.LottoFactory;
import lotto.domain.lottoTicket.LottoNumber;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningValue;
import lotto.util.ConvertInput;

import java.util.List;
import java.util.Map;

public class LottoManager {
    private Money money;
    private Buyer buyer;
    private WinningLotto winningLotto;

    public LottoManager(int money) {
        LottoFactory.getInstance();
        this.money = new Money(money);
        this.buyer = new Buyer(this.money.calculateLottoTicketCount());
    }

    public void setWinningLotto(String numbers, int bonusNumber) {
        List<LottoNumber> winningLottoNumbers = ConvertInput.convertLottoNumbers(numbers);
        this.winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);
    }

    public Money getMoney() {
        return money;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }
}