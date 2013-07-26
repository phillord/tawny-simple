;; The contents of this file are subject to the LGPL License, Version 3.0.
;;
;; Copyright (C) 2013, Phillip Lord, Newcastle University
;;
;; This program is free software: you can redistribute it and/or modify it
;; under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or (at your
;; option) any later version.
;;
;; This program is distributed in the hope that it will be useful, but WITHOUT
;; ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
;; FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
;; for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns simple.only-individual
  (:use [tawny.owl])
  (:require [tawny.reasoner :as r])
  )


;; ## Introduction
;;
;; This demonstrates the inferences that can be made on individuals as a
;; result of an only, or existential relationship.

;; ## Premable
;;
;; Set up the reasoner and define the ontology.
(r/reasoner-factory :hermit)
(defontology only-ind)

;; ## Main classes
;;
;; We define a simple class `A` with a single universal relationship to a
;; second class `X`. Neither of these classes need to be defined, both are
;; primitive.
(defoproperty r)
(defclass X)
(defclass A
  :subclass (only r X))

;; ## Individuals
;;
;; We now define an individual `i` but say no more about it.
(defindividual i)

;; The individual `k` on the other hand is described as having a relationship
;; `r` to individual `i`.
(defindividual k
  :type A
  :fact (fact r i))


;; and a second untyped individual `j` with the same relationship to `i`.
(defindividual j
  :fact (fact r i))

;; ## Inferences
;;
;; So can we conclude anything? We can say relatively little about the class
;; `A` other than it has a single instance `k` which we have directly asserted
;; anyway.
(r/instances A)

;; However, we can say something about class `X`. We have stated that for
;; class `A` while if need not have an `r` relationship to anything at all, if
;; it does have this relationship, it can only be to instances of `X`. As a
;; result of this, we can conclude that `i` is an instance of `X`. We do not
;; made the same conclusion about `j` because, while it has the same
;; relationship to `i`, it is not of class `A`, so the universal restriction
;; does not apply.
(r/instances X)


;; ## Conclusions
;;
;; The universal restriction limits what can be on the incoming end of a
;; relationship, for a given class. The inferences that can be made are
;; perhaps less intuitive, as in this case a relationship is infered between
;; two entities, `X` and `i` with no frames or definitions themselves, but
;; which are used in the definition of other entities.

;; ## See Also
;;
;; [some-individual](#simple.some-individual) for inferences that can be made
;; as a result of `some` statements.
