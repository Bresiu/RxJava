import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * @author Michal Brewczak
 *         <p>
 *         Class showing differencies between Observer and Subscriber
 */
public class ObserverVsSubscriber {

	private void test() {
		Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
		observable.subscribe(new Observer<Integer>() {
			@Override
			public void onCompleted() {
				System.out.println("[observer] on completed");
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(Integer integer) {
				System.out.println("[observer] onNext: " + integer);
				if (integer == 3) {
					System.out.println("[observer] observer can't be usubscribed here.");
				}
			}
		});
		observable.subscribe(new Subscriber<Integer>() {
			@Override
			public void onCompleted() {
				System.out.println("[subscriber] on completed");
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(Integer integer) {
				System.out.println("[subscriber] onNext: " + integer);
				if (integer == 3) {
					System.out.println("[subscriber] subscriber can be usubscribed here.");
					unsubscribe();
				}
			}
		});
	}

	public static void main(String[] args) {
		ObserverVsSubscriber observerVsSubscriber = new ObserverVsSubscriber();
		observerVsSubscriber.test();
	}
}
