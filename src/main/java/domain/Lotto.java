package domain;

import domain.lottonumber.LottoNumber;
import domain.money.Money;

import java.util.Objects;
import java.util.Set;

public class Lotto {
    static final int NUMBER_OF_LOTTO_NUMBERS = 6;
    public static final Money PRICE = Money.amountOf(1000);

    Set<LottoNumber> lottoNumbers;

    Lotto(Set<LottoNumber> lottoNumbers) {
        validateNumberOf(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateNumberOf(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != NUMBER_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException("로또를 발급받으려면 서로 다른 6개의 숫자가 있어야 합니다.");
        }
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}