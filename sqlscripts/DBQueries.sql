USE FishTank;

SELECT * FROM Fish;
SELECT * FROM Fish
	LEFT JOIN WaterTypes ON Fish.waterId = WaterTypes.id;