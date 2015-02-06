package utils;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class Pipeline<IN,OUT> implements Stream<OUT> {

	public static class Head<OUT> extends Pipeline<Object,OUT> {
		public Head(Iterator<?> source) {
			super(source);
		}
		protected OUT get() {
			return (OUT)this.sourceIterator.next();
		}
	}
	static class Filter<R> extends Pipeline<R,R> {
		final Predicate<? super R> predicate;
		public Filter(Pipeline<?, R> previousStage, Predicate<? super R> predicate) {
			super(previousStage);
			this.predicate = predicate;
		}
		protected R get() {
			R elem = (R)this.previousStage.get();
			while (elem != null && ! predicate.test(elem))
				elem = (R)this.previousStage.get();
			return elem;
		}
	}
	static class Map<IN,OUT> extends Pipeline<IN,OUT> {
		final Function<? super IN, ? extends OUT> mapper;
		public Map(Pipeline<?,IN> previousStage, Function<? super IN, ? extends OUT> mapper) {
			super(previousStage);
			this.mapper = mapper;
		}
		protected OUT get() {
			IN elem = (IN)this.previousStage.get();
			return elem == null ? null : mapper.apply(elem);
		}
	}
	
	protected final Pipeline sourceStage;

	protected final Pipeline previousStage;
    
	protected Pipeline nextStage;

	protected Iterator<?> sourceIterator;

	protected Supplier<? extends Iterator<?>> sourceSupplier;

    private Pipeline(Iterator<? extends IN> source) {
        this.previousStage = null;
        this.sourceIterator = source;
        this.sourceStage = this;
    }

    private Pipeline(Pipeline<?,IN> previousStage) {
        previousStage.nextStage = this;
        this.previousStage = previousStage;
        this.sourceStage = previousStage.sourceStage;
    }

	protected abstract OUT get();

	@Override
	public Stream<OUT> filter(Predicate<? super OUT> predicate) {
		return new Filter<OUT>(this, predicate);
	}

	@Override
	public <R> Stream<R> map(Function<? super OUT, ? extends R> mapper) {
		return new Map<OUT,R>(this, mapper);
	}

	@Override
	public void forEach(Consumer<? super OUT> action) {
		for(OUT elem = this.get(); elem != null; elem = this.get()) {
			action.accept(elem);
		}
	}
}
