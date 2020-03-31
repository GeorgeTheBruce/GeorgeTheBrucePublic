--2/20/20 - 2/25

--2020-02-20  -- 2020-02-25


Select *
from site s

join reservation r on r.site_id = s.site_id
join campground c on c.campground_id = s.campground_id

where c.campground_id = 1 and s.site_id 

not in(
select r.site_id from reservation r
        where(r.to_date < '2020-02-18' and r.from_date > '2020-02-18')-- left check
        or   (r.to_date > '2020-02-20' and r.from_date < '2020-02-20') -- right check
        or   (r.to_date < '2020-02-18' and r.from_date < '2020-02-20')
)
;





--2/20/20 - 2/25

--2020-02-20  -- 2020-02-25


Select *, ((r.to_date ::date - r.from_date ::date) * daily_fee) as cost_of_stay

from site s
join reservation r on r.site_id = s.site_id
join campground c on c.campground_id = s.campground_id
where c.campground_id = 1 and s.site_id 
not in(
select r.site_id from reservation r
        where(r.to_date < '2020-02-18' and r.from_date > '2020-02-18')
        or   (r.to_date > '2020-02-20' and r.from_date < '2020-02-20') 
        or   (r.to_date < '2020-02-18' and r.from_date < '2020-02-20')
)
;


BEGIN TRANSACTION;
        INSERT INTO reservation (site_id, name, from_date, to_date, create_date) 
        VALUES (1, 'Daniel Yoo', '2020-01-01', '2020-05-01', '2020-02-01');
        
        SELECT * FROM reservation WHERE name = 'Daniel Yoo';
ROLLBACK;





